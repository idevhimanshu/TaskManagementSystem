package com.example.TaskManagementSystem.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskDTO {
	
	@NotEmpty(message = "Title cannot be empty")
	private String tile;
	@NotEmpty(message = "Title cannot be empty")
	private String description;
	@NotEmpty
	private LocalDateTime dueData;

}
