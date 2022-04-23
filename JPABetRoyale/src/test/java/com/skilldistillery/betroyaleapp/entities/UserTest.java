package com.skilldistillery.betroyaleapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private User user;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPABetRoyale");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("Testing User Entity Mapping")
	void test() {
//		SELECT username, password, first_name, last_name FROM user WHERE id=1;
//		+----------+----------+------------+-----------+
//		| username | password | first_name | last_name |
//		+----------+----------+------------+-----------+
//		| admin    | admin    | bet        | royale    |
//		+----------+----------+------------+-----------+
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
		assertEquals("admin", user.getPassword());
		assertEquals("bet", user.getFirstName());
		assertEquals("royale", user.getLastName());
	}
	
	@Test
	@DisplayName("Testing OneToMany User(1) ---> Wager(Many)")
	void test2() {
		
		assertNotNull(user);
		assertNotNull(user.getWagers());
		assertTrue(user.getWagers().size() > 0);
	}

}
