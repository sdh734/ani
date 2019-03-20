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

    @PostMapping({"/upload"})
    public String upload(@RequestParam("file") final MultipartFile file) {
        final String filename = file.getOriginalFilename();
        // 本地目录
        final String path = "G:\\upload\\";
        // 服务器目录
        // final String path = "/www/wwwroot/ass/upload/";
        try {
            file.transferTo(new File(path + filename));
            final edu.smxy.associationmanagement.domain.File f =
                    new edu.smxy.associationmanagement.domain.File();
            f.setFilename(filename);
            f.setFilepath(path);
            this.fileService.uploadFile(f);
            return "OK";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @GetMapping({"/downloadbyid"})
    public void downloadFileById(final int id, final HttpServletResponse response) {
        final edu.smxy.associationmanagement.domain.File file = this.fileService.searchFileById(id);
        final String filepath = file.getFilepath();
        String filename = file.getFilename();
        try (final InputStream inputStream = new FileInputStream(new File(filepath + filename));
             final OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            filename = URLEncoder.encode(file.getFilename(), "UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据传入的文件id字符串下载压缩包
     *
     * @param idstr    id字符串
     * @param response
     */
    @RequestMapping({"/downloadFileByIdArray"})
    public void downloadFileByIdArray(final String idstr, final HttpServletResponse response) {
        final String[] idarr = idstr.split(" ");
        final List<edu.smxy.associationmanagement.domain.File> files =
                new ArrayList<edu.smxy.associationmanagement.domain.File>();
        final List<String> paths = new ArrayList<String>();
        for (int i = 0; i < idarr.length; ++i) {
            files.add(this.fileService.searchFileById(Integer.valueOf(idarr[i])));
        }
        for (final edu.smxy.associationmanagement.domain.File file : files) {
            paths.add(file.getFilepath() + file.getFilename());
        }
        // 本地目录
        final String zipBasePath = "G:\\upload\\ziptemp\\";
        // 服务器目录
        // final String zipBasePath = "/www/wwwroot/ass/upload/";

        String zipName = "temp.zip";
        final String zipFilePath = zipBasePath + File.separator + zipName;
        final File zip = new File(zipFilePath);
        if (!zip.exists()) {
            try {
                zip.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
            new ZipUtils().zipFile(zipBasePath, zipName, zipFilePath, paths, zos);
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (final InputStream inputStream = new FileInputStream(zip);
             final OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            zipName = URLEncoder.encode(zipName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + zipName);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @GetMapping({"/searchbyid"})
    public edu.smxy.associationmanagement.domain.File searchById(final int id) {
        final edu.smxy.associationmanagement.domain.File file = this.fileService.searchFileById(id);
        return file;
    }

    @GetMapping({"/searchbyeventid"})
    public List<edu.smxy.associationmanagement.domain.File> searchFileByEventId(final int eventid) {
        List<edu.smxy.associationmanagement.domain.File> files =
                this.fileService.searchFileByEvent(eventid);
        return files;
    }

    @GetMapping({"/searchbyauthorid"})
    public List<edu.smxy.associationmanagement.domain.File> searchFileByAuthorId(final int authorid) {
        return this.fileService.searchFileByAuthor(authorid);
    }

    @RequestMapping({"/getAllFileByAssid"})
    public JSONResult getAllFileByAssid(final int assid) {
        final List<edu.smxy.associationmanagement.domain.File> files =
                this.fileService.getAllFileByAssid(assid);
        return getJsonResult(files);
    }

    private JSONResult getJsonResult(List<edu.smxy.associationmanagement.domain.File> files) {
        final List<FileResult> fileResults = new ArrayList<>();
        for (final edu.smxy.associationmanagement.domain.File file : files) {
            final FileResult fileResult = new FileResult(file);
            fileResult.setAuthorname(
                    this.associationService.selectByPrimaryKey(file.getAuthorid()).getAssociationName());
            fileResult.setEventname(
                    this.eventService.selectByPrimaryKey(file.getEventid()).getEventName());
            fileResults.add(fileResult);
        }
        return JSONResult.build(200, "ok", fileResults);
    }

    @RequestMapping({"/getAllFile"})
    public JSONResult getAllFile() {
        final List<edu.smxy.associationmanagement.domain.File> files = this.fileService.getAllFile();
        return getJsonResult(files);
    }

    @RequestMapping({"/updateFiletoEvent"})
    public JSONResult updateFiletoEvent(
            @RequestParam("file") final MultipartFile file,
            @RequestParam("authorid") final String authorid,
            @RequestParam("eventid") final String eventid,
            @RequestParam("id") final String id) {
        final String filename = file.getOriginalFilename();
        // 本地目录
        final String path = "G:\\upload\\eventfile\\";
        // 服务器目录
        // final String zipBasePath = "/www/wwwroot/ass/upload/";
        try {
            file.transferTo(new File(path + filename));
            final edu.smxy.associationmanagement.domain.File file2 =
                    new edu.smxy.associationmanagement.domain.File();
            file2.setFilename(filename);
            file2.setFilepath(path);
            file2.setAuthorid(Integer.valueOf(authorid));
            file2.setEventid(Integer.valueOf(eventid));
            file2.setId(Integer.valueOf(id));
            final int result = this.fileService.updateByPrimaryKey(file2);
            if (result > 0) {
                return JSONResult.build(200, "ok", null);
            }
            return JSONResult.build(500, "更新失败", null);
        } catch (IOException e) {
            e.printStackTrace();
            return JSONResult.build(500, "更新失败", null);
        }
    }
}
