package com.github.shoothzj.demo.base.util;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static String getFilePath(String filename) {
        return Resources.getResource(filename).getPath();
    }

    /**
     * 获取文件类型
     * @param fileName 文件名
     * @return
     */
    public static String getFileType(String fileName) {
        int lastIndexOf = fileName.lastIndexOf('.');
        return fileName.substring(lastIndexOf + 1);
    }


    public static void removeRecursive(String dirPath) {
        removeRecursive(Paths.get(dirPath));
    }

    /**
     * 递归删除文件
     * @param path 路径
     */
    public static void removeRecursive(Path path) {
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (exc == null) {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                    return super.postVisitDirectory(dir, exc);
                }
            });
        } catch (IOException e) {
            log.error("walk file error, exception is {}", ExceptionUtil.getException(e));
        }
    }

    public static void inputStream2File(InputStream inputStream, String file) {
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File(file));
        } catch (Exception e) {
            log.error("convert to file error, exception is {}", ExceptionUtil.getException(e));
        }
    }

    public static void inputStream2File(InputStream inputStream, File file) {
        try {
            FileUtils.copyInputStreamToFile(inputStream, file);
        } catch (Exception e) {
            log.error("convert to file error, exception is {}", ExceptionUtil.getException(e));
        }
    }

}
