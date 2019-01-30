package edu.smxy.associationmanagement.controller.testcontroller;

import org.springframework.boot.autoconfigure.*;
import edu.smxy.associationmanagement.services.file.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.multipart.*;
import javax.servlet.http.*;
import org.apache.tomcat.util.http.fileupload.*;
import java.io.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@EnableAutoConfiguration
@RequestMapping({ "/file" })
public class TestFileController
{
    @Autowired
    private FileService fileService;
    
    @PostMapping({ "/upload" })
    public String upload(final MultipartFile fileupload) {
        final String filename = fileupload.getOriginalFilename();
        final String path = "G:\\upload\\";
        try {
            fileupload.transferTo(new File(path + filename));
            final edu.smxy.associationmanagement.domain.File file = new edu.smxy.associationmanagement.domain.File();
            file.setFilename(filename);
            file.setFilepath(path);
            this.fileService.uploadFile(file);
            return "OK";
        }
        catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
    
    @GetMapping({ "/downloadbyid" })
    public void downloadFileById(final int id, final HttpServletResponse response) {
        final edu.smxy.associationmanagement.domain.File file = this.fileService.searchFileById(id);
        final String filepath = file.getFilepath();
        final String filename = file.getFilename();
        try (final InputStream inputStream = new FileInputStream(new File(filepath + filename));
             final OutputStream outputStream = (OutputStream)response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + filename);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    @ResponseBody
    @GetMapping({ "/searchbyid" })
    public edu.smxy.associationmanagement.domain.File searchById(final int id) {
        final edu.smxy.associationmanagement.domain.File file = this.fileService.searchFileById(id);
        return file;
    }
    
    @ResponseBody
    @GetMapping({ "/searchbyeventid" })
    public List<edu.smxy.associationmanagement.domain.File> searchFileByEventId(final int eventid) {
        return this.fileService.searchFileByEvent(eventid);
    }
    
    @ResponseBody
    @GetMapping({ "/searchbyauthorid" })
    public List<edu.smxy.associationmanagement.domain.File> searchFileByAuthorId(final int authorid) {
        return this.fileService.searchFileByAuthor(authorid);
    }
}
