package com.cmapi.services; 

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.cmapi.persistence.ContentItem;

/**
* Interface for the Email Service
*/
public interface EmailService {    

    public void sendContentList(List<ContentItem> contentItems);

}