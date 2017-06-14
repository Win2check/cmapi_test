package com.cmapi.services;
 
import java.util.List;
import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import com.cmapi.persistence.*;
import com.cmapi.model.CMModel;

/**
* Interface for the Content Item Service
*/
public interface ContentItemService {
     
    ContentItem findById(Long id);
 
    ContentItem findByName(String name);
 
    void save(ContentItem contentItem);
 
    List<ContentItem> findAll();

    List<ContentItem> findByCreatedTimestampBetween(Timestamp start, Timestamp end);

    void saveContent(MultipartFile file) throws IOException;

    void saveMetadata(CMModel model);
     
}