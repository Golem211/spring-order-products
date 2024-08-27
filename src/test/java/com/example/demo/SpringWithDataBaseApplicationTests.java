package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

class SpringWithDataBaseApplicationTests {

	public static final int ID = 20;

	@Autowired
	CustomerService customerService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void saved_in_database() {
		Customer expectedCustomer = createAna();
		Customer savedCustomer = customerService.save(expectedCustomer);

		Customer actualCustomer = customerService.findById(savedCustomer.getId());
		assertEquals(savedCustomer, actualCustomer);
	}

	private static Customer createAna() {
		Customer ana = new Customer();
		ana.setName("Mihaela");
		ana.setBirthDate(LocalDate.of(2021, 4, 5));
		ana.setEmail("mihaelaBBBDAS@gm.com");
		return ana;
	}

	@Test
	void save_with_API() throws Exception {
		Customer ana = createAna();
		mockMvc.perform(post("/customers")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(ana)))
				.andExpect(status().isOk());

		//TODO object mapper to read From string in customers

		ResultMatcher ana2 = content().string(containsString("Ana"));

		ResultActions perform = mockMvc.perform(get("/customers"));
		MvcResult result = perform
				.andExpect(status().isOk())
				.andReturn();
		//.andExpect(ana2);
		String jsonResult = result.getResponse().getContentAsString();
		List<Customer> customerList = objectMapper.readValue(jsonResult, new TypeReference<List<Customer>>() {
		});

		perform.andExpect(content().string(containsString(ana.getEmail())));
	}
}
