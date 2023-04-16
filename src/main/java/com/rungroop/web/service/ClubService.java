package com.rungroop.web.service;

import java.util.List;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;

public interface ClubService {

	List<ClubDto> findAllClubs();
	Club saveClub(ClubDto clubDto);
	ClubDto findClubById(long clubId);
	void updateClub(ClubDto clubDto);
	void delete(Long clubId);
	List<ClubDto> searchClubs(String query);
}
