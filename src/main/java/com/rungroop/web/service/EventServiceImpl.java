package com.rungroop.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rungroop.web.dto.EventDto;
import com.rungroop.web.mapper.EventMapper;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.Event;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.repository.EventRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

	private EventRepository eventRepository;
	private ClubRepository clubRepository;
	private EventMapper eventMapper;
	
	@Override
	public void createEvent(Long clubId, EventDto eventDto) {
		
		Club club = clubRepository.findById(clubId).get();
		Event event = eventMapper.mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
		
	}

	@Override
	public List<EventDto> findAllEvents() {
		List<Event> events = eventRepository.findAll();
		return events.stream().map(event -> eventMapper.mapToEventDto(event)).collect(Collectors.toList());
	}

	@Override
	public EventDto findByEventId(Long eventId) {
		Event event = eventRepository.findById(eventId).get();
		return eventMapper.mapToEventDto(event);
		
		
	}

	@Override
	public void updateEvent(EventDto eventDto) {
		Event event = eventMapper.mapToEvent(eventDto);
		eventRepository.save(event);
	}

	@Override
	public void delete(Long eventId) {
		eventRepository.deleteById(eventId);
	}


	
}






