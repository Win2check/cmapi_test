package com.cmapi.services; 

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Properties;
import java.util.Iterator;
import java.lang.StringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.Session;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cmapi.persistence.ContentItem;

/**
* Implementation class for the Email Service
*/
@Service("EmailService")
public class EmailServiceImpl implements EmailService {   

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendContentList(List<ContentItem> contentItems) {
        MimeMessage mail = javaMailSender.createMimeMessage();
        ObjectMapper mapper = new ObjectMapper();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);           
            helper.setTo("shehamizat@gmail.com");            
            helper.setFrom("shehamizat@gmail.com");
            helper.setSubject("New Documents Report");
            Iterator iterator = contentItems.iterator();
            StringBuilder strbuilder = new StringBuilder();  
            while(iterator.hasNext()) {
                strbuilder.append(mapper.writeValueAsString(iterator.next()));
            }
            helper.setText(strbuilder.toString());
            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

}