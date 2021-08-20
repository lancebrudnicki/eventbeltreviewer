package com.lance.EventBeltReviewer.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lance.EventBeltReviewer.models.Event;
import com.lance.EventBeltReviewer.models.User;
import com.lance.EventBeltReviewer.services.EventService;
import com.lance.EventBeltReviewer.services.StateService;
import com.lance.EventBeltReviewer.services.UserService;

@Controller
public class Events {
	@Autowired
	EventService eventService;
	@Autowired
	StateService stateService;
	@Autowired
	UserService userService;

	
	
	@PostMapping("/createevent")
	public String createEvent(@Valid @ModelAttribute("createevent") Event event,
							BindingResult result, HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("user_id");
		if(result.hasErrors()) {
	    		User thisUser = userService.findUserById(id);
	    		model.addAttribute("states", stateService.getAllStates());
	    		model.addAttribute("events", eventService.getAllEvents());
	    		model.addAttribute("user", thisUser);
			return "homePage.jsp";
		}else {
			User user = userService.findUserById((Long)session.getAttribute("user_id"));
			event.setUser(user);
			eventService.createEvent(event);
			return "redirect:/events";
			}
		}
	
	@PostMapping("/events/id")
	public String showEvent(@ModelAttribute("event")Event event) {
		
		return "showEvent.jsp";
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String destory(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		Event eventcheck = eventService.findEvent(id);
		if(userId.equals(eventcheck.getUser().getId()) ) {
    		eventService.deleteEvent(id);
    		return "redirect:/events";    			
    	}else {
    		return "redirect:/events";	
    	}
	}
	
	
	@GetMapping("/events/{id}/edit")
	public String editEventPage(@ModelAttribute("event") Event event, @PathVariable("id") Long id, Model model) {
		Event events = eventService.findEvent(id);
		
		model.addAttribute("event", events);
		model.addAttribute("states", stateService.getAllStates());
		return "editEvent.jsp";
	}
	
	
	@RequestMapping(value="/events/{id}/edit", method=RequestMethod.PUT)
	public String editEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, 
							@PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
    	if(userId != null) {
    		if(result.hasErrors()) {
    			model.addAttribute("states", stateService.getAllStates());
    			return "editEvent.jsp";
    		}else {
    			eventService.updateEvent(event);
    			return "redirect:/events";    			  					
    		}	    	
    	}else {
    		return "redirect:/events";	
    	}
	}
	
	@RequestMapping("/events/join/{id}")
	public String joinEvent(@PathVariable("id") Long id, HttpSession session) {
		Event event = eventService.findEvent(id);
		User user = userService.findUserById((Long) session.getAttribute("user_id"));
		event.getJoinedUsers().add(user);
		eventService.updateEvent(event);
		return "redirect:/events/";
	}
	
	@GetMapping("/events/{id}")
	public String showEventPage(@PathVariable("id") Long id,Model model) {
		
		
		model.addAttribute("event", eventService.findEvent(id));
		return "showEvent.jsp";
	}
	
	@RequestMapping("/cancel/{id}")
	    public String cancel(@PathVariable("id") Long id, HttpSession session) {
		Event event = eventService.findEvent(id);
		User user = userService.findUserById((Long) session.getAttribute("user_id"));
		event.getJoinedUsers().remove(user);
		eventService.updateEvent(event);
		return "redirect:/events";
    }
	
	
	
}
