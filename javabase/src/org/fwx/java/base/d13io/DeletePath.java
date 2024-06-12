package org.fwx.java.base.d13io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @ClassName DeletePath
 * @Description 删除文件夹
 * @Author Fwx
 * @Date 2023/5/18 16:06
 * @Version 1.0
 */
public class DeletePath {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeletePath.class);

    /**
     * jdk7 后，使用 Files 删除文件夹
     */
    @Test
    public void deletePath() {
        Path path = Paths.get("D:\\0\\day01 - 副本");

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                /**
                 * 删除文集
                 * @param file
                 *          a reference to the file
                 * @param attrs
                 *          the file's basic attributes
                 *
                 * @return
                 * @throws IOException
                 */
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    LOGGER.info("****** app log, delete file: " + file);
                    return FileVisitResult.CONTINUE;
                }

                /**
                 * 删除路径
                 * @param dir
                 *          a reference to the directory
                 * @param exc
                 *          {@code null} if the iteration of the directory completes without
                 *          an error; otherwise the I/O exception that caused the iteration
                 *          of the directory to complete prematurely
                 *
                 * @return
                 * @throws IOException
                 */
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    LOGGER.info("****** app log, delete dir: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
