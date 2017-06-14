package com.cmapi.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.cmapi.persistence.*;
import com.cmapi.services.*;

/**
* A Scheduler to check for new files each hour, and email them
* as a JSON list.
*/
@Component
public class ScheduledReports {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledReports.class);

    @Autowired
    ContentItemService contentItemService;

    @Autowired
    EmailService emailService;
   
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 3600000)
    public void NewContentReport() {
        logger.info("Running the scheduler for New Content Report at {}", dateFormat.format(new Date()));       
        List<ContentItem> contentItems = contentItemService.findByCreatedTimestampBetween(new Timestamp(System.currentTimeMillis() - 3600 * 1000), new Timestamp(System.currentTimeMillis()));
        if (!contentItems.isEmpty()) {
        	logger.info("{} documents were uploaded in the previous hour.", contentItems.size());
        	emailService.sendContentList(contentItems);
        }
    }
}