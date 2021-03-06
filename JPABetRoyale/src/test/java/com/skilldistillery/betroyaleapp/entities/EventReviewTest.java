package com.skilldistillery.betroyaleapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventReviewTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private EventReview review;

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
		EventReviewId erid = new EventReviewId();
		erid.setBettableEventId(1);
		erid.setUserId(2);
		review = em.find(EventReview.class, erid);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		review = null;
	}
	
	@Test
	@DisplayName("ManyToMany EventReview --> Bettable Event")
	void test1() {
		assertNotNull(review);
		assertEquals(2,review.getId().getUserId());
		assertEquals(1,review.getId().getBettableEventId());
	}
	
	@Test
	@DisplayName("ManyToMany EventReview --> Bettable Event")
	
//	SELECT name FROM bettable_event WHERE id=1;
//	+--------------+
//	| name         |
//	+--------------+
//	| does it work |
//	+--------------+
	
	void test2() {
		assertNotNull(review);
		assertNotNull(review.getBettableEvent());
		assertEquals("does it work", review.getBettableEvent().getName());
		
	}
	

	
	
	
}
