package com.enigma.demo.service;

import com.enigma.demo.model.FileDB;
import com.enigma.demo.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    @Autowired
    FileDBRepository fileDBRepository;

    public FileDB store(String id, MultipartFile file, String productName, Date date, Integer status) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(id, fileName, productName, file.getContentType(), date, status, file.getBytes());

        return fileDBRepository.save(fileDB);

    }


    public FileDB getFile(String id){
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles(){
        return fileDBRepository.findAll().stream();
    }
}
