package com.skilldistillery.betroyaleapp.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.betroyaleapp.entities.BettableEvent;

@Service
public class EventsDaoImpl implements EventsDAO {

	public static void main(String[] args) {
		EventsDaoImpl dao = new EventsDaoImpl();
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	
	public List<BettableEvent> displayBettableEvents() {
		List<BettableEvent> betEvents = new ArrayList<BettableEvent>();
		try {
			String jpql = "SELECT b FROM BettableEvent b";

			betEvents = em.createQuery(jpql, BettableEvent.class).getResultList();

			
			
		} catch (Exception e) {
			return betEvents;
		}
		return betEvents;
	}

	
	
	
	
	
	
}
