package com.cmapi.services;
 
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

import com.cmapi.persistence.*;
import com.cmapi.model.CMModel;

/**
* Implementation class for the Content Item Service
*/
@Service("ContentItemService")
@Transactional
public class ContentItemServiceImpl implements ContentItemService{
 
    @Autowired
    private ContentItemRepository contentItemRepository;
 
    private static String UPLOAD_FOLDER = "c://contentrepository//";

    public ContentItem findById(Long id) {
        return contentItemRepository.findOne(id);
    }
 
    public ContentItem findByName(String name) {
        return contentItemRepository.findByName(name);
    }
 
    public void save(ContentItem contentItem) {
        contentItemRepository.save(contentItem);
    }    
 
    public List<ContentItem> findAll(){
        return contentItemRepository.findAll();
    }  

    public List<ContentItem> findByCreatedTimestampBetween(Timestamp start, Timestamp end) {
        return contentItemRepository.findByCreatedTimestampBetween(start, end); 
    }   
 
    public void saveContent(MultipartFile file) throws IOException {               
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
        Files.write(path, bytes);                
    }

    public void saveMetadata(CMModel model) {          
        ContentItem contentItem = new ContentItem();
        contentItem.setName(model.getCmName());
        contentItem.setAuthor(model.getCmAuthor());      
        contentItem.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        contentItem.setType(model.geCmType());
        contentItem.setFileName(model.getFile().getOriginalFilename());
        contentItemRepository.save(contentItem);
    }

}