package com.ey.todo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ey.todo.entity.Todo;
import com.ey.todo.entity.TodoDto;
import com.ey.todo.exception.NoRecordsFetchedException;
import com.ey.todo.repository.TodoRepo;

@SpringBootTest
public class TodoServiceTests {

	@MockBean
	private TodoRepo todoRepo;

	@Autowired
	private TodoService todoService;

	private Todo todo1;
	private Todo todo2;

	List<Todo> todoList;

	@BeforeEach
	public void setUp() {
		todoList = new ArrayList<>();
		todo1 = new Todo(1L, "todoItem1", "status1", "date1", true);
		todo2 = new Todo(2L, "todoItem2", "status2", "date2", true);
		todoList.add(todo1);
		todoList.add(todo2);
	}

	@AfterEach
	public void tearDown() {
		todo1 = todo2 = null;
		todoList = null;
	}

	@Test
	@DisplayName("Deleting the existing Todo Test")
	void deleteExistingTodoTest() {
		long id = 2;
		when(todoRepo.findById(id)).thenReturn(Optional.of(todo1));
		assertDoesNotThrow(() -> todoService.deleteTodo(Long.toString(id)));
	}
	
	@Test
	@DisplayName("Deleting the Non existing Todo Test")
	void deleteNonExistingTodoTest() {
		long id = 2;
		when(todoRepo.findById(id)).thenReturn(Optional.empty());
		assertThrows(NoRecordsFetchedException.class,() -> todoService.deleteTodo(Long.toString(id)));
	}
	
	@Test
	@DisplayName("Fetching the existing Todo Test by ID")
	void retrivingExistingTodoTest() {
		long id = 2;
		when(todoRepo.findById(id)).thenReturn(Optional.of(todo1));
		assertDoesNotThrow(() -> todoService.getTodoById(Long.toString(id)));
	}
	
	@Test
	@DisplayName("Fetching the Non existing Todo Test by ID")
	void retrivingNonExistingTodoTest() {
		long id = 2;
		when(todoRepo.findById(id)).thenReturn(Optional.empty());
		assertThrows(NoRecordsFetchedException.class,() -> todoService.getTodoById(Long.toString(id)));
	}
	@Test
	@DisplayName("Fetching all the  existing Todo")
	void retrivingAllExistingTodoTest() {
		when(todoRepo.findAll()).thenReturn(todoList);
		assertDoesNotThrow(() -> todoService.getAllTodo());
	}
	
	@Test
	@DisplayName("Create a new Todo")
	void creatingnewTodoTest() {
		TodoDto t1 = new TodoDto("todoItem2", "status2", true);
		when(todoRepo.save(t1)).thenReturn(todo1);
		assertDoesNotThrow(() -> todoService.generateTodo(t1));
	}
	
	@Test
	@DisplayName("Update a exisiting Todo")
	void updateexisitngTodoTest() {
		TodoDto t1 = new TodoDto("todoItem3", "status2", false);
		when(todoRepo.save(t1)).thenReturn(new Todo(2L,"todoItem3", "status2", "new time",false));
		assertDoesNotThrow(() -> todoService.updateTodo(2L,t1));
	}
	
	
}
