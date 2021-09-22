package com.ey.todo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String todoItem;
	private String status;
	private String updatedDate;
	private boolean setReminder;
	
	
	public Todo(String todoItem, String status, String updatedDate,boolean setReminder) {
		super();
		this.todoItem = todoItem;
		this.status = status;
		this.updatedDate = updatedDate;
		this.setReminder = setReminder;
	}
}
