package com.ey.todo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.todo.entity.Todo;
import com.ey.todo.entity.TodoDto;
import com.ey.todo.exception.InputFormatException;
import com.ey.todo.service.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;
	
	// GET, POST , PUT, DELETE Services endpoints 
    
    
    @GetMapping("/getTodo")
    public List<Todo> getAllTodo() {
    	LOGGER.info("Successfully generated Todo using the generateTodo()");
        return todoService.getAllTodo();
        
    }
    
    @GetMapping("/getTodo/{id}")
    public Optional<Todo> getTodoById(@PathVariable String id) {
    	if(!StringUtils.isNumeric(id)) {
			throw new InputFormatException(String.format("Input Format is wrong Please enter an numeric value: %s", id), String.format("http://localhost:8080/api/DeleteTodo/%s",id));
		}
    	LOGGER.info("Successfully generated Todo using the generateTodo()");
        return todoService.getTodoById(id);
    }
    
    @PostMapping("/generate")
    public ResponseEntity<Todo> generateTodo(@RequestBody TodoDto todo) {
    	
    	LOGGER.info("Started the Generate todo");
    	if(todo.equals(null)) {
    		LOGGER.error("Enter valid data");
    	}
        Todo  todoGenerate= todoService.generateTodo(todo);
        LOGGER.info("Successfully generated Todo using the generateTodo()");
            return new ResponseEntity<>(todoGenerate, HttpStatus.OK);
      
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String id, @RequestBody TodoDto todo) {
    	
    	LOGGER.info("Started the update todo");
    	if(todo.equals(null)) {
    		LOGGER.error("Enter valid data");
    	}
    	if(!StringUtils.isNumeric(id)) {
			throw new InputFormatException(String.format("Input Format is wrong Please enter an numeric value: %s", id), String.format("http://localhost:8080/api/update/%s",id));
		}
    	Long idval = Long.valueOf(id);
        Todo  todoUpdate= todoService.updateTodo(idval, todo);
        LOGGER.info("Successfully Updated Todo using the generateTodo()");
            return new ResponseEntity<>(todoUpdate, HttpStatus.OK);
      
    }
    
    
    @DeleteMapping("/deleteTodo/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
    	LOGGER.info("Deleted Todo Task with Id:"+ id);
        todoService.deleteTodo(id); 
    }
    
    

}
