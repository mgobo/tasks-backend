package br.ce.wcaquino.tasks.apitest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ce.wcaquino.taskbackend.model.Task;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend/";
	}
	
	@Test
	public void deveRetornarTarefas() {
		RestAssured.given()
				   .when()
				   		.get("/todo")
				   .then()
				   		.log().all()
				   		.statusCode(200)
				   ;
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() throws JsonProcessingException {
		for(int i = 1; i <= 10; i++) {
			Task task = new Task();
			task.setTask("Task test "+i);
			task.setDueDate(LocalDate.now());
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule())
													.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
													
			mapper.setDateFormat(sdf);
			
			String json = mapper.writeValueAsString(task);
			System.out.println(json);
			
			RestAssured.given()
					   .body(json)
					   .contentType(ContentType.JSON)
					   .when()
					   	.post("/todo")
					   .then()
					   	.log().all()
					   	.statusCode(201);
		}
	}
	
}
