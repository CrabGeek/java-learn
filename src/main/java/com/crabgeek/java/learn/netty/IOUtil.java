package com.crabgeek.java.learn.netty;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.Channel;
import java.util.Objects;

public class IOUtil {
    public static String getResourcePath(String fileName) {
       if (StringUtils.isEmpty(fileName)) {
           return StringUtils.EMPTY;
       }
        URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);
       if (Objects.isNull(resource)) {
           return StringUtils.EMPTY;
       }
        return resource.getPath();
    }

    public static String buildResourcePath(String fileName) {
        URL resource = ClassLoader.getSystemClassLoader().getResource("");
        if (Objects.isNull(resource)) {
            return StringUtils.EMPTY;
        }
        String path = resource.getPath();
        return path += fileName;
    }

    public static void closeQuietly(Channel channel) {
        if (Objects.nonNull(channel)) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeQuietly(InputStream inputStream) {
        if (Objects.nonNull(inputStream)) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeQuietly(OutputStream outputStream) {
        if (Objects.nonNull(outputStream)) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
