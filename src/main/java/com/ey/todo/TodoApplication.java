package com.ey.todo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ey.todo.entity.Todo;
import com.ey.todo.repository.TodoRepo;


@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	public TodoRepo todoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Collection<Todo> todos = Arrays.asList(new Todo("Learn Spring boot", "Not Completed",LocalDate.now().toString(),true), new Todo("Make an appointment with the doctor", "Completed",LocalDate.now().toString(),false), new Todo("Go for a Walk", "Not Completed",LocalDate.now().toString(),true), new Todo("Cook Dinner", "Completed",LocalDate.now().toString(),false));
		todos.forEach(todoRepository::save);
		
	}

}
