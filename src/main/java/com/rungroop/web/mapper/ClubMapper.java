package com.rungroop.web.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClubMapper {

	private EventMapper eventMapper;
	
	public ClubDto mapToClubDto(Club club) {
		ClubDto clubDto = ClubDto.builder()
					.id(club.getId())
					.title(club.getTitle())
					.photoUrl(club.getPhotoUrl())
					.content(club.getContent())
					.createdBy(club.getCreatedBy())
					.createdOn(club.getCreatedOn())
					.updateOn(club.getUpdateOn())
					.events(club.getEvents().stream().map(event -> eventMapper.mapToEventDto(event)).collect(Collectors.toList()))
					.build();
		return clubDto;
	}

	public Club mapToClub(ClubDto clubDto) {
		Club club = Club.builder()
					.id(clubDto.getId())
					.title(clubDto.getTitle())
					.photoUrl(clubDto.getPhotoUrl())
					.content(clubDto.getContent())
					.createdBy(clubDto.getCreatedBy())
					.createdOn(clubDto.getCreatedOn())
					.updateOn(clubDto.getUpdateOn())
					.build();
		return club;
	}

	
}
