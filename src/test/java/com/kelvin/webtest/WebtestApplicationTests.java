package com.kelvin.webtest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebtestApplicationTests {

	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void setup () {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testOrder() throws Exception {
		mockMvc.perform(get("/order")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk());
//			.andExpect(jsonPath("$").value("success"));
	}

}
