package edu.smxy.associationmanagement.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: associationmanagement
 * @description: 多文件下载打包成zip格式
 * @author: SDH
 * @create: 2019-01-14 02:01
 */
public class ZipUtils {
    /**
     * 压缩文件
     *
     * @param zipBasePath 临时压缩文件基础路径
     * @param zipName     临时压缩文件名称
     * @param zipFilePath 临时压缩文件完整路径
     * @param filePaths   需要压缩的文件路径集合
     * @throws IOException
     */
    public String zipFile(
            String zipBasePath,
            String zipName,
            String zipFilePath,
            List<String> filePaths,
            ZipOutputStream zos)
            throws IOException {
        // 循环读取文件路径集合，获取每一个文件的路径
        for (String filePath : filePaths) {
            File inputFile = new File(filePath); // 根据文件路径创建文件
            if (inputFile.exists()) { // 判断文件是否存在
                if (inputFile.isFile()) { // 判断是否属于文件，还是文件夹
                    // 创建输入流读取文件
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));

                    // 将文件写入zip内，即将文件进行打包
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));

                    // 写入文件的方法，同上
                    int size = 0;
                    byte[] buffer = new byte[1024]; // 设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {
                        zos.write(buffer, 0, size);
                    }
                    // 关闭输入输出流
                    zos.closeEntry();
                    bis.close();

                } else { // 如果是文件夹，则使用穷举的方法获取文件，写入zip
                    try {
                        File[] files = inputFile.listFiles();
                        List<String> filePathsTem = new ArrayList<String>();
                        for (File fileTem : files) {
                            filePathsTem.add(fileTem.toString());
                        }
                        return zipFile(zipBasePath, zipName, zipFilePath, filePathsTem, zos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
