package com.sbasly.microservice.order;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:latest");

	static {
		mysqlContainer.start();
	}

	@LocalServerPort
	private int port;

	@DynamicPropertySource
	static void configureTestProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", () -> mysqlContainer.getJdbcUrl());
		registry.add("spring.datasource.username", () -> mysqlContainer.getUsername());
		registry.add("spring.datasource.password", () -> mysqlContainer.getPassword());
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void saveOrderEntity() {
		String requestBody = """
				{
					"skuCode": "Laptop",
					"price": 1500,
					"quantity": 101
				}
				""";

		RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/api/orders")
				.then()
				.statusCode(HttpStatus.CREATED.value())
				.body("id", Matchers.notNullValue())
				.body("skuCode", Matchers.equalTo("Laptop"))
				.body("quantity", Matchers.is(101))
				.body("price", Matchers.is(1500));
	}
}
