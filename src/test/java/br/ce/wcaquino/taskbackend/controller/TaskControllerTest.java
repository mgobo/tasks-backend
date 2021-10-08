package br.ce.wcaquino.taskbackend.controller;


import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

	@Mock
	private TaskRepo taskRepo;
	
	@InjectMocks
	private TaskController taskController;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void naoSalvarTarefasSemDescricao() {
		try {
			Task todo = new Task();
			todo.setDueDate(LocalDate.now());
			this.taskController.save(todo);
		}catch(ValidationException ex) {
			Assert.assertEquals("Fill the task description", ex.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		try {
			Task todo = new Task();
			todo.setTask("Descricao");
			this.taskController.save(todo);
		}catch(ValidationException ex) {
			Assert.assertEquals("Fill the due date", ex.getMessage());
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		try {
			Task todo = new Task();
			todo.setTask("Descricao");
			todo.setDueDate(LocalDate.of(2010, 01, 01));
			this.taskController.save(todo);
		}catch(ValidationException ex) {
			Assert.assertEquals("Due date must not be in past", ex.getMessage());
		}
	}
	
	@Test
	public void deveSalvarTarefa() throws ValidationException{
		Task todo = new Task();
		todo.setTask("Descricao");
		todo.setDueDate(LocalDate.now());
		this.taskController.save(todo);
		
		Mockito.verify(taskRepo).save(todo);
	}
	
}
