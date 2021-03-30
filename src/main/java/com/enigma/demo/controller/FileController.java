package com.enigma.demo.controller;

import com.enigma.demo.message.ResponseFile;
import com.enigma.demo.message.ResponseMessage;
import com.enigma.demo.model.FileDB;
import com.enigma.demo.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8083")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;

    @PutMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file,
                                                      @RequestParam("productName") String productName,
                                                      @RequestParam("date")Date date,
                                                      @RequestParam("status") Integer status,
                                                      @RequestParam("id") String id){
        String message = "";
        try {
            fileStorageService.store(id, file, productName, date, status);
            message = "Uploaded succeccfully " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }
        catch (Exception e){
            message = "Could not upload file "+ file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(){
        List<ResponseFile> files = fileStorageService.getAllFiles().map(dbFile ->{
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    dbFile.getProductName(),
                    fileDownloadUri,
                    dbFile.getDate(),
                    dbFile.getType(),
                    dbFile.getStatus(),
                    dbFile.getData().length
            );
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        FileDB fileDB = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
