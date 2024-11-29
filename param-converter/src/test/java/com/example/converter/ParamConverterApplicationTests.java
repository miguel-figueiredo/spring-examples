package com.example.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class ParamConverterApplicationTests {

	@BeforeEach
	void setUp() {
		final HelloController subject = new HelloController();
		final ApplicationConversionService conversionService = new ApplicationConversionService();
		conversionService.addConverter(new MyEnumConverter());
		standaloneSetup(MockMvcBuilders
				.standaloneSetup(subject)
				.setConversionService(conversionService));
	}

	@Test
	void contextLoads() {
		given()
		.when()
			.get("/hello?myEnum=a")
		.then()
			.statusCode(200);
	}

}
