package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
class DemoApplicationTests {
    @Autowired
	private GraphQlTester graphQlTester;

	@Test
	void contextLoads() {

		var query = """
				query{
					hello
				}
				""";

		String name = graphQlTester.document(query).execute().path("hello").entity(String.class).get();
		Assertions.assertTrue(name.contains("hello world"));

		//Assertions.assertEquals(name , Matchers.contains("hello world"));

	}

}
