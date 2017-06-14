 package com.cmapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.cmapi.model.CMModel;
import com.cmapi.persistence.*;
import com.cmapi.services.*;

/**
* The API Controller class
*/
@RestController
public class CMRestController {

    private final Logger logger = LoggerFactory.getLogger(CMRestController.class);    
    private static String UPLOAD_FOLDER = "c://contentrepository//";
   
    @Autowired
    ContentItemService contentItemService;
   
/**
* REST for uploading a file and its metadata. 
* Curl example: curl -F "file=@c://test.txt" 
* -F "cmName=test" -F "cmAuthor=test" -F "cmCreatedDate=test" -F "cmType=test" 
* http://localhost:8080/cmapi/uploadcontent
*/
    @PostMapping("/cmapi/uploadcontent")
    public ResponseEntity<?> uploadContentModel(@ModelAttribute CMModel model) {        
        try {
            saveContent(model.getFile());
            saveMetadata(model);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Content has been successfully uploaded.", HttpStatus.OK);
    }

/**
* Call for the ContentItemServive.saveContent()
*/
    private void saveContent(MultipartFile file) {
        logger.info("Uploading file: " + file.getOriginalFilename());
        try {
            contentItemService.saveContent(file);     
         } catch (Exception e) {
            logger.error(e.getMessage());
         }                   
    }

/**
* Call for the ContentItemServive.saveMetadata()
*/
     private void saveMetadata(CMModel model) {  
        logger.info("Uploading data for: " + model.getFile().getOriginalFilename());
        contentItemService.saveMetadata(model);  
    }

/**
* REST for retrieving the metadata of all existing content (JSON).
* Created to be used by a search interface.
* Curl example: curl http://localhost:8080/cmapi/getallcontentdata
*/
    @GetMapping("/cmapi/getallcontentdata")
    public ResponseEntity<List<ContentItem>> getAllContentItems() {
        List<ContentItem> contentItems = contentItemService.findAll();
        if (contentItems.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);           
        }
        return new ResponseEntity<List<ContentItem>>(contentItems, HttpStatus.OK);
    }

/**
* REST for retrieving  the metadata of a specific content (JSON) by Id 
* Created to be used by a search interface.
* Curl example: curl http://localhost:8080/cmapi/getcontentbyid/1
*/
    @GetMapping("/cmapi/getcontentdatabyid/{id}")
    public ResponseEntity<ContentItem> getContentItemById(@PathVariable("id") long id) {
        ContentItem contentItem = contentItemService.findById(id);
        if (contentItem == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);           
        }
        return new ResponseEntity<ContentItem>(contentItem, HttpStatus.OK);
    }

/**
* REST for retrieving  the metadata of a specific content (JSON) by name 
* Created to be used by a search interface.
* Curl example: curl http://localhost:8080/cmapi/getcontentdatabyname/name_used_when_file_was_uploaded
*/
    @GetMapping("/cmapi/getcontentdatabyname/{name}")
    public ResponseEntity<ContentItem> getContentItemByName(@PathVariable("name") String name) {
        ContentItem contentItem = contentItemService.findByName(name);
        if (contentItem == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);            
        }
        return new ResponseEntity<ContentItem>(contentItem, HttpStatus.OK);
    }

/**
* REST for retrieving the actual file by id
* Curl example: curl http://localhost:8080/cmapi/getcontentbyid/1
*/
    @GetMapping("/cmapi/getcontentbyid/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("id") long id) {
        ContentItem contentItem = contentItemService.findById(id);
        if (contentItem != null) {
            try {
                Path path = Paths.get(UPLOAD_FOLDER + contentItem.getFileName());
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
                return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
