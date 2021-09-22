package com.ey.todo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ey.todo.entity.Todo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryIntegrationTest {
	
	 @Autowired
	  private TodoRepo todorepository;

	    @Test
	    void showTodo() {
	    List<Todo> todoList = todorepository.findAll();
	    assertThat(todoList).size().isGreaterThan(0);
	    }
	    
	    
	    @Test
	    void findTodoByIdExistingTest() {

	        Long id = (long) 2;
	        Optional<Todo> todo = todorepository.findById(id);
	        assertThat(todo.orElse(null)).isNotNull();
	    }
	    
	    @Test
	    @Rollback(value = false)
	    void createNewTodoTest() {

	        Todo todo1 = new Todo("todoItem1", "status1","date1",false);
	        Todo newOne = todorepository.save(todo1);
	        assertNotNull(newOne);
	    }
	    

	    @Test
	    void deleteExistingtodoTest() {

	        long id = 4;
	        boolean isPresentBeforeDelete = todorepository.findById(id).isPresent();
	        todorepository.deleteById(id);
	        boolean notPresentAfterDelete = todorepository.findById(id).isPresent();

	        assertTrue(isPresentBeforeDelete);
	        assertFalse(notPresentAfterDelete);

	    }
	    
	    

}
