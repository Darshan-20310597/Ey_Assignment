package com.ey.todo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoDto {
	
	private String todoItem;
	private String status;
	private boolean setReminder;
	
	public TodoDto(String todoItem, String status,boolean setReminder) {
		super();
		this.todoItem = todoItem;
		this.status = status;
		this.setReminder = setReminder;
	}

	public String getTodoItem() {
		return todoItem;
	}

	public void setTodoItem(String todoItem) {
		this.todoItem = todoItem;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSetReminder() {
		return setReminder;
	}

	public void setSetReminder(boolean setReminder) {
		this.setReminder = setReminder;
	}
	
	
	
	
}
