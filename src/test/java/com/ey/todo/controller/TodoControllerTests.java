package com.ey.todo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ey.todo.entity.Todo;
import com.ey.todo.entity.TodoDto;
import com.ey.todo.repository.TodoRepo;
import com.ey.todo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@Profile(value = "test")
@AutoConfigureMockMvc
@SpringBootTest
public class TodoControllerTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;


	@MockBean
	private TodoService todoService;

	@MockBean
	private TodoRepo todoRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private Todo todo1;
	private Todo todo2;

	List<Todo> todoList;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
	@DisplayName("Get Todo based on ID test Api Test")
	public void testGetTodoByIdFound() throws Exception {

		when(todoService.getTodoById("1")).thenReturn(Optional.of(todo1));
		mockMvc.perform(get("/api/getTodo/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.status").value("status1"));
	}
	
	@Test
	@DisplayName("Get All Todo Api Test")
	public void testGetTodoAll() throws Exception {

		when(todoService.getAllTodo()).thenReturn(todoList);
		mockMvc.perform(get("/api/getTodo")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].id").value("1")).andExpect(jsonPath("$[0].status").value("status1"));
	}
	@Test
	@DisplayName("Get All Todo Api Test")
	public void testPostTodo() throws Exception {
		
		
		TodoDto testTodo = new TodoDto("Status_updated","Status_updated",false);
		when(todoService.getTodoById("1")).thenReturn(Optional.of(todo1));
		when(todoService.generateTodo(testTodo)).thenReturn(todo1);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/generate")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.objectMapper.writeValueAsString(testTodo));
		
		mockMvc.perform(mockRequest).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.status").value("status1"));
	}
	
	@Test
	public void deleteTodoById_success() throws Exception {
	    when(todoService.getTodoById("1")).thenReturn(Optional.of(todo1));

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/api/deleteTodo/2")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}

}
