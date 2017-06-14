package com.cmapi.model;

import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;

/**
* Data Model used to map the metadata entered when uploading a file to an object
*/
public class CMModel {

    private long cmId;
    private String cmName;
    private String cmAuthor;   
    private String cmType;
   
    private MultipartFile file;

    public long getCmId() {
        return cmId;
    }

    public void setCmId(long cmId) {
        this.cmId = cmId;
    }

    public String getCmName() {
        return cmName;
    }

    public void setCmName(String cmName) {
        this.cmName = cmName;
    }

    public String getCmAuthor() {
        return cmAuthor;
    }

    public void setCmAuthor(String cmAuthor) {
        this.cmAuthor = cmAuthor;
    }    

    public String geCmType() {
        return cmType;
    }

    public void setCmType(String cmType) {
        this.cmType = cmType;
    }
    
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "CMModel{" +
                "cmName='" + cmName + '\'' +
                "cmAuthor='" + cmAuthor + '\'' +               
                "cmType='" + cmType + '\'' +
                ", file=" + file +
                '}';
    }
}
