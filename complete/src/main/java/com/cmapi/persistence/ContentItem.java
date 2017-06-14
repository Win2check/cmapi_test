package com.cmapi.persistence;
 
import javax.persistence.*;
import java.sql.Timestamp;

/**
* Entity object to represent a content item.
*/
@Entity
public class ContentItem {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String name;
    private String author;
    private Timestamp createdTimestamp;
    private String type;
    private String fileName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String geType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}