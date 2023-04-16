package com.rungroop.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.rungroop.web.models.UserEntity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubDto {

	private Long id;
	@NotEmpty(message = "Club title should not be empty")
	private String title;
	@NotEmpty(message = "Photo link should not be empty")
	private String photoUrl;
	@NotEmpty(message = "Content should not be empty")
	private String content;
	private UserEntity createdBy;
	private LocalDateTime createdOn;
	private LocalDateTime updateOn;
	private List<EventDto> events;
	
}
