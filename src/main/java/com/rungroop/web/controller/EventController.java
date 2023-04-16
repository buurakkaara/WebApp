package com.rungroop.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.security.SecurityUtil;
import com.rungroop.web.service.EventService;
import com.rungroop.web.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EventController {

	private EventService eventService;
	private UserService userService;
	
	
	@GetMapping("/events")
	public String eventList(Model model) {
		UserEntity user = new UserEntity();
		List<EventDto> events = eventService.findAllEvents();
		String username = SecurityUtil.getSessionUser();
	      if(username != null) {
	          user = userService.findByUsername(username);
	          model.addAttribute("user", user);
	      }
	      model.addAttribute("user", user);
		model.addAttribute("events", events);
		return "events-list";
		
	}
	
	@GetMapping("/events/{eventId}")
	public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
		UserEntity user = new UserEntity();
		EventDto eventDto = eventService.findByEventId(eventId);
		String username = SecurityUtil.getSessionUser();
	    if(username != null) {
	        user = userService.findByUsername(username);
	        model.addAttribute("user", user);
	    }
	    model.addAttribute("club", eventDto);
	    model.addAttribute("user", user);
		model.addAttribute("event", eventDto);
		return "event-detail";
		
	}
	
	@GetMapping("/events/{clubId}/new")
	public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
		
		Event event = new Event();
		model.addAttribute("clubId" , clubId);
		model.addAttribute("event", event);
		return "events-create";
		
	}
	
	@GetMapping("/events/{eventId}/edit")
	public String editEventForm(@PathVariable("eventId")Long eventId, Model model) {
		EventDto event = eventService.findByEventId(eventId);
		model.addAttribute("event", event);
		return "events-edit";
	}
	
	@PostMapping("/events/{clubId}")
	public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto, Model model) {
		
		eventService.createEvent(clubId, eventDto);
		return "redirect:/clubs/" + clubId;
		
	}
	
	@PostMapping("/events/{eventId}/edit")
	public String updateClub(@PathVariable("eventId") Long eventId,
							@Valid @ModelAttribute("event") EventDto eventDto,
							BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "events-edit";
		}
		
		EventDto event = eventService.findByEventId(eventId);
		eventDto.setId(eventId);
		eventDto.setClub(event.getClub());
		eventService.updateEvent(eventDto);
		return "redirect:/events";
	}
	
	@GetMapping("/events/{eventId}/delete")
	public String deleteEvent(@PathVariable("eventId")Long eventId) {
		eventService.delete(eventId);
		return "redirect:/events";
	}
	
	
}




