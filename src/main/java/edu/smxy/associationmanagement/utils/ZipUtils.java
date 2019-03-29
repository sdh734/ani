package edu.smxy.associationmanagement.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
	public String zipFile(
			final String zipBasePath,
			final String zipName,
			final String zipFilePath,
			final List<String> filePaths,
			final ZipOutputStream zos)
			throws IOException {
		for (final String filePath : filePaths) {
			final File inputFile = new File(filePath);
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
					zos.putNextEntry(new ZipEntry(inputFile.getName()));
					int size = 0;
					final byte[] buffer = new byte[1024];
					while ((size = bis.read(buffer)) > 0) {
						zos.write(buffer, 0, size);
					}
					zos.closeEntry();
					bis.close();
				} else {
					try {
						final File[] files = inputFile.listFiles();
						final List<String> filePathsTem = new ArrayList<String>();
						for (final File fileTem : files) {
							filePathsTem.add(fileTem.toString());
						}
						return this.zipFile(zipBasePath, zipName, zipFilePath, filePathsTem, zos);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
}
