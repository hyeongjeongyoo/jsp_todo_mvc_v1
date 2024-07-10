package com.tenco.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TodoDTO {
	
	private int id;
	private int userId;
	private String title;
	private String description;
	private String dueDate;
	private String completed;

}
