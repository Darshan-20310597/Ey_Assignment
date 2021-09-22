package com.ey.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ey.todo.entity.Todo;
import com.ey.todo.entity.TodoDto;


@Repository
public interface TodoRepo extends JpaRepository<Todo, Long>{

	Todo save(TodoDto todo);

}
