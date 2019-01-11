package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.services.file.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
@RestController
@ResponseBody
@EnableAutoConfiguration
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        String path = "G:\\upload\\";
        try {
            file.transferTo(new File(path + filename));
            edu.smxy.associationmanagement.domain.File f = new edu.smxy.associationmanagement.domain.File();
            f.setFilename(filename);
            f.setFilepath(path);
            fileService.uploadFile(f);
            return "OK";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @GetMapping("/downloadbyid")
    public void downloadFileById(int id, HttpServletResponse response) {
        edu.smxy.associationmanagement.domain.File file = fileService.searchFileById(id);
        String filepath = file.getFilepath();
        String filename = file.getFilename();
        try (
                //jdk7新特性，可以直接写到try()括号里面，java会自动关闭
                InputStream inputStream = new FileInputStream(new File(filepath + filename));
                OutputStream outputStream = response.getOutputStream()
        ) {
            //指明为下载
            response.setContentType("application/x-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @ResponseBody
    @GetMapping("/searchbyid")
    public edu.smxy.associationmanagement.domain.File searchById(int id) {
        edu.smxy.associationmanagement.domain.File file = fileService.searchFileById(id);
        return file;
    }

    @ResponseBody
    @GetMapping("/searchbyeventid")
    public List<edu.smxy.associationmanagement.domain.File> searchFileByEventId(int eventid) {
        return fileService.searchFileByEvent(eventid);
    }

    @ResponseBody
    @GetMapping("/searchbyauthorid")
    public List<edu.smxy.associationmanagement.domain.File> searchFileByAuthorId(int authorid) {
        return fileService.searchFileByAuthor(authorid);
    }
}
