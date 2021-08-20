package com.lance.EventBeltReviewer.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lance.EventBeltReviewer.models.Event;

public interface EventRepo extends CrudRepository<Event, Long> {

	List <Event> findAll();
}
