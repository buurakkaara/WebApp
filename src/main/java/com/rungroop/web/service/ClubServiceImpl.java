package com.rungroop.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.mapper.ClubMapper;
import com.rungroop.web.models.Club;
import com.rungroop.web.models.UserEntity;
import com.rungroop.web.repository.ClubRepository;
import com.rungroop.web.repository.UserRepository;
import com.rungroop.web.security.SecurityUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClubServiceImpl implements ClubService{

	private ClubRepository clubRepository;
	private ClubMapper clubMapper;
	private UserRepository userRepository;
	
	@Override
	public List<ClubDto> findAllClubs() {
		List<Club> clubs = clubRepository.findAll();
		return clubs.stream().map(club -> clubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}

	@Override
	public Club saveClub(ClubDto clubDto) {
		String username = SecurityUtil.getSessionUser();
		UserEntity user = userRepository.findByUsername(username);
		Club club = clubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		return clubRepository.save(club);
	}

	@Override
	public ClubDto findClubById(long clubId) {
		
		Club club = clubRepository.findById(clubId).get();	
		return clubMapper.mapToClubDto(club);
	}
	
	@Override
	public void updateClub(ClubDto clubDto) {
		
		String username = SecurityUtil.getSessionUser();
		UserEntity user = userRepository.findByUsername(username);
		Club club = clubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		clubRepository.save(club);
	}
	
	@Override
	public void delete(Long clubId) {
		clubRepository.deleteById(clubId);
	}
	
	@Override
	public List<ClubDto> searchClubs(String query) {
		List<Club> clubs = clubRepository.searchClubs(query);
		return clubs.stream().map(club -> clubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}
	
	



	
	
	
	
}













