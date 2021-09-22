package com.ey.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ey.todo.entity.Todo;
import com.ey.todo.entity.TodoDto;


public interface TodoService {
	
	
	public Todo generateTodo(TodoDto todo);
	public Todo updateTodo(Long id,TodoDto todo);
	public List<Todo> getAllTodo();
	public Optional<Todo> getTodoById(String id);
	public void deleteTodo(String id);
	

}
