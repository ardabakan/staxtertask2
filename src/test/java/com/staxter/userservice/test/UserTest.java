package com.staxter.userservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.staxter.userservice.domain.LoginRequest;
import com.staxter.userservice.domain.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	User TEST_USER_TO_INSERT_1 = User.builder().firstName("Name1").lastName("Lastname1").userName("user1")
			.plainTextPassword("12345").build();

	User TEST_USER_TO_INSERT_2 = User.builder().firstName("Name2").lastName("Lastname2").userName("user2")
			.plainTextPassword("12345").build();

	LoginRequest LOGIN_REQUEST_WITH_GOOD_CREDENTIALS = LoginRequest.builder().userName("user1")
			.plainTextPassword("12345").build();

	LoginRequest LOGIN_REQUEST_WITH_BAD_CREDENTIALS = LoginRequest.builder().userName("user1")
			.plainTextPassword("67890").build();

	@Test
	public void test_1_createFirstUser() throws Exception {
		String uri = "/userservice/register";

		String inputJson = super.mapToJson(TEST_USER_TO_INSERT_1);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		String content = mvcResult.getResponse().getContentAsString();
		User userInserted = super.mapFromJson(content, User.class);

		assertEquals(201, status);
		// 1st user should have an id = 1
		assertEquals(userInserted.getId(), "1");

		assertEquals(userInserted.getUserName(), TEST_USER_TO_INSERT_1.getUserName());
		assertTrue(userInserted.getPlainTextPassword() == null);
		assertTrue(userInserted.getHashedPassword() == null);

	}

	@Test
	public void test_2_createSecondUser() throws Exception {
		String uri = "/userservice/register";

		String inputJson = super.mapToJson(TEST_USER_TO_INSERT_2);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		String content = mvcResult.getResponse().getContentAsString();
		User userInserted = super.mapFromJson(content, User.class);

		assertEquals(201, status);
		// 2nd user should have an id = 2
		assertEquals(userInserted.getId(), "2");

	}

	@Test
	public void test_3_tryToCreateSameUserAndHaveException() throws Exception {
		String uri = "/userservice/register";

		String inputJson = super.mapToJson(TEST_USER_TO_INSERT_1);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(409, status);
	}

	@Test
	public void test_4_tryToLoginWithGoodCredentials() throws Exception {
		String uri = "/userservice/login";

		String inputJson = super.mapToJson(LOGIN_REQUEST_WITH_GOOD_CREDENTIALS);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		User userLoggedIn = super.mapFromJson(content, User.class);

		assertEquals(userLoggedIn.getUserName(), LOGIN_REQUEST_WITH_GOOD_CREDENTIALS.getUserName());

	}

	@Test
	public void test_5_tryToLoginWithBadCredentialsAndHaveException() throws Exception {
		String uri = "/userservice/login";

		String inputJson = super.mapToJson(LOGIN_REQUEST_WITH_BAD_CREDENTIALS);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(403, status);
	}

}
