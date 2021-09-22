package com.ey.todo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.todo.entity.Todo;
import com.ey.todo.entity.TodoDto;
import com.ey.todo.exception.NoRecordsFetchedException;
import com.ey.todo.repository.TodoRepo;

@Service
public class TodoServiceImpl implements TodoService{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	@Autowired
	private TodoRepo todoRepo;
	
	@Override
	public Todo generateTodo(TodoDto todo) {
		LOGGER.info("Started the generateTodo ServiceImpl");
		Todo newTodo = new Todo(todo.getTodoItem(), todo.getStatus(), LocalDate.now().toString(),todo.isSetReminder());
		return todoRepo.save(newTodo);
	}

	@Override
	public List<Todo> getAllTodo() {
		// TODO Auto-generated method stub
		LOGGER.info("Started the getAllTodo ServiceImpl");
		return todoRepo.findAll();
	}

	@Override
	public void deleteTodo(String id) {
		Long idToDelete = Long.valueOf(id);
		if (todoRepo.findById(Long.valueOf(idToDelete)).isEmpty()) {
            throw new NoRecordsFetchedException(String.format("No records to deleted with the id: %s", idToDelete), String.format("http://localhost:8080/api/DeleteTodo/%s",idToDelete));
        }
		todoRepo.deleteById(idToDelete);
			
	}


	@Override
	public Optional<Todo> getTodoById(String id) {
		// TODO Auto-generated method stub
		LOGGER.info("Started the getAllTodobyId ServiceImpl");
		Long getId = Long.valueOf(id);
		Optional<Todo> TodoById = todoRepo.findById(Long.valueOf(id));
		if (TodoById.isEmpty()) {
			throw new NoRecordsFetchedException("No records for the ID "+getId, "http://localhost:8080/api/getTodo/"+getId);
		}
		return TodoById;
	}

	@Override
	public Todo updateTodo(Long id, TodoDto todo) {
		return todoRepo.findById(id).map(todoupdate -> {todoupdate.setTodoItem(todo.getTodoItem()); 
		todoupdate.setStatus(todo.getStatus()); todoupdate.setUpdatedDate(LocalDate.now().toString()); 
		todoupdate.setSetReminder(todo.isSetReminder()); return todoRepo.save(todoupdate);}).orElseGet(()-> {return todoRepo.save(todo);});
	}

}
