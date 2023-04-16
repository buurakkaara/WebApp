package com.rungroop.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rungroop.web.models.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {

	@Query("SELECT c from Club c WHERE c.title LIKE CONCAT('%' , :query, '%')")
	List<Club> searchClubs(String query);
	
}
