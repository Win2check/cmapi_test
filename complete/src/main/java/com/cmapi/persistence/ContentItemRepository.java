package com.cmapi.persistence;
 
import java.util.List;
 
import org.springframework.data.repository.CrudRepository;
import java.sql.Timestamp;

/**
* The CRUD interface for the Content Item Entity
* Required and used by Spring Boot Data Repository to implement the different 
* CRUD operations.
*/
public interface ContentItemRepository extends CrudRepository<ContentItem, Long> {

	ContentItem findByName(String name);
	ContentItem findOne(long id);
	List<ContentItem> findAll();
	List<ContentItem> findByCreatedTimestampBetween(Timestamp start, Timestamp end);
 
}