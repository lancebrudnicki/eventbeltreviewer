package com.lance.EventBeltReviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lance.EventBeltReviewer.models.Event;
import com.lance.EventBeltReviewer.repos.EventRepo;

@Service
public class EventService {
	
	@Autowired
	EventRepo eventRepo;
	
	
	public List<Event> getAllEvents(){
		return eventRepo.findAll();
	}
	
	
	public Event createEvent(Event event) {
		return eventRepo.save(event);
	}
	
	public Event findEvent(Long id) {
		Optional<Event> optionalEvent = eventRepo.findById(id);
		if(optionalEvent.isPresent()) {
			return optionalEvent.get();
		}else {
			return null;
		}
	}

	
	public Event updateEvent(Event event) {	
		return eventRepo.save(event);
	}
	
	
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
}
