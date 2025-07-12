package com.techflow.management.App_ToDo;

import com.techflow.management.App_ToDo.Controllers.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AppToDoApplicationTests {

	@Autowired
	private MainController controller;

	// This test checks if the Spring application context loads successfully
	// and if the MainController bean is correctly injected.
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}