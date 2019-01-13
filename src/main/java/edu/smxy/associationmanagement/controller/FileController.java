package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.FileResult;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.event.EventService;
import edu.smxy.associationmanagement.services.file.FileService;
import edu.smxy.associationmanagement.utils.ZipUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

@RestController
@ResponseBody
@EnableAutoConfiguration
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private EventService eventService;
    @Autowired
    private AssociationService associationService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        String path = "G:\\upload\\";
        try {
            file.transferTo(new File(path + filename));
            edu.smxy.associationmanagement.domain.File f =
                    new edu.smxy.associationmanagement.domain.File();
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
        try (InputStream inputStream = new FileInputStream(new File(filepath + filename));
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            // 设置文件名
            filename = URLEncoder.encode(file.getFilename(), "UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
            // 把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/downloadFileByIdArray")
    public void downloadFileByIdArray(String idstr, HttpServletResponse response) {
        String[] idarr = idstr.split(" ");
        List<edu.smxy.associationmanagement.domain.File> files = new ArrayList<>();
        List<String> paths = new ArrayList<>();
        for (int i = 0; i < idarr.length; i++) {
            files.add(fileService.searchFileById(Integer.valueOf(idarr[i])));
        }
        for (edu.smxy.associationmanagement.domain.File file : files) {
            paths.add(file.getFilepath() + file.getFilename());
        }
        String zipBasePath = "G:/upload/";
        String zipName = "temp.zip";
        String zipFilePath = zipBasePath + File.separator + zipName;
        File zip = new File(zipFilePath);
        if (!zip.exists()) {
            try {
                zip.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建zip文件输出流
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
            new ZipUtils().zipFile(zipBasePath, zipName, zipFilePath, paths, zos);
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = new FileInputStream(zip);
             OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            // 设置文件名
            zipName = URLEncoder.encode(zipName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + zipName);
            // 把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/searchbyid")
    public edu.smxy.associationmanagement.domain.File searchById(int id) {
        edu.smxy.associationmanagement.domain.File file = fileService.searchFileById(id);
        return file;
    }

    @GetMapping("/searchbyeventid")
    public List<edu.smxy.associationmanagement.domain.File> searchFileByEventId(int eventid) {
        return fileService.searchFileByEvent(eventid);
    }

    @GetMapping("/searchbyauthorid")
    public List<edu.smxy.associationmanagement.domain.File> searchFileByAuthorId(int authorid) {
        return fileService.searchFileByAuthor(authorid);
    }

    @RequestMapping("/getAllFileByAssid")
    public JSONResult getAllFileByAssid(int assid) {
        List<edu.smxy.associationmanagement.domain.File> files = fileService.getAllFileByAssid(assid);
        List<FileResult> fileResults = new ArrayList<>();
        for (edu.smxy.associationmanagement.domain.File file : files) {
            FileResult fileResult = new FileResult(file);
            fileResult.setAuthorname(
                    associationService.selectByPrimaryKey(file.getAuthorid()).getAssociationName());
            fileResult.setEventname(eventService.selectByPrimaryKey(file.getEventid()).getEventName());
            fileResults.add(fileResult);
        }
        return JSONResult.build(200, "ok", fileResults);
    }

    @RequestMapping("/getAllFile")
    public JSONResult getAllFile() {
        List<edu.smxy.associationmanagement.domain.File> files = fileService.getAllFile();
        List<FileResult> fileResults = new ArrayList<>();
        for (edu.smxy.associationmanagement.domain.File file : files) {
            FileResult fileResult = new FileResult(file);
            fileResult.setAuthorname(
                    associationService.selectByPrimaryKey(file.getAuthorid()).getAssociationName());
            fileResult.setEventname(eventService.selectByPrimaryKey(file.getEventid()).getEventName());
            fileResults.add(fileResult);
        }
        return JSONResult.build(200, "ok", fileResults);
    }

    @RequestMapping("/updateFiletoEvent")
    public JSONResult updateFiletoEvent(
            @RequestParam("file") MultipartFile file,
            @RequestParam("authorid") String authorid,
            @RequestParam("eventid") String eventid,
            @RequestParam("id") String id) {
        String filename = file.getOriginalFilename();
        String path = "G:\\upload\\";
        try {
            file.transferTo(new File(path + filename));
            edu.smxy.associationmanagement.domain.File file1 =
                    new edu.smxy.associationmanagement.domain.File();
            file1.setFilename(filename);
            file1.setFilepath(path);
            file1.setAuthorid(Integer.valueOf(authorid));
            file1.setEventid(Integer.valueOf(eventid));
            file1.setId(Integer.valueOf(id));
            int result = fileService.updateByPrimaryKey(file1);
            if (result > 0) {
                return JSONResult.build(200, "ok", null);
            } else {
                return JSONResult.build(500, "更新失败", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONResult.build(500, "更新失败", null);
    }
}
