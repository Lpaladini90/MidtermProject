package com.skilldistillery.betroyaleapp.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.skilldistillery.betroyaleapp.entities.BettableEvent;
import com.skilldistillery.betroyaleapp.entities.Contender;
import com.skilldistillery.betroyaleapp.entities.EventComment;
import com.skilldistillery.betroyaleapp.entities.User;
import com.skilldistillery.betroyaleapp.entities.Wager;

import org.springframework.stereotype.Service;

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

	@Transactional
	@Override
	public EventComment addComment(EventComment comment) {
		em.persist(comment);
		em.flush();
		return comment;
	}

	@Override
	public BettableEvent findEventById(int id) {
		
		return em.find(BettableEvent.class, id);
	}

	@Override
	public User findUserById(int id) {
		return em.find(User.class, id);
	}
	
	
	@Override
	public List<BettableEvent> displayActiveBettableEvents() {
		List<BettableEvent> activeBetEvents = new ArrayList<BettableEvent>();

		try {
			String jpql = "SELECT b FROM BettableEvent b WHERE b.active = 1";

			activeBetEvents = em.createQuery(jpql, BettableEvent.class).getResultList();
		} catch (Exception e) {

			return activeBetEvents;
		}

		return activeBetEvents;
	}
	
	@Override
	public List<BettableEvent> displayExpiredBettableEvents() {
		List<BettableEvent> expiredBetEvents = new ArrayList<BettableEvent>();
		try {
			String jpql = "SELECT b FROM BettableEvent b WHERE b.active = 0";

			expiredBetEvents = em.createQuery(jpql, BettableEvent.class).getResultList();
		} catch (Exception e) {

			return expiredBetEvents;
		}

		return expiredBetEvents;
	}

	// single user created list
	@Override
	public List<BettableEvent> displayUserCreatedBettableEvents(int userId) {
		List<BettableEvent> userCreated = new ArrayList<BettableEvent>();
		try {
		String jpql = "select b from BettableEvent b where b.user.id = :id";
		
		userCreated = em.createQuery(jpql, BettableEvent.class)
				.setParameter("id", userId)
				.getResultList();
		
		}catch (Exception e) {
			return userCreated;
		}
		return userCreated;
	}

	
	@Transactional
	@Override
	public Contender updateContender(int contenderId, boolean isWinner) {
		
		Contender contender = em.find(Contender.class, contenderId);
		contender.setIsWinner(isWinner);
		em.persist(contender);
		em.flush();
		
		
		return contender;
		
	}
	
	@Transactional
	@Override
	public BettableEvent updateBettableEvent(BettableEvent event) {
		em.persist(event);
		em.flush();
		
		return event;
	}

	@Override
	public Map<Integer, CalculatedWinnings> calculateLeaderBoard() {
		List<BettableEvent> events = null;
		String jpql = "SELECT b FROM BettableEvent b where b.completion = true";
		try {
			events = em.createQuery(jpql, BettableEvent.class).getResultList();
			events.forEach(System.out::println);
			
		}catch(Exception e){ 
			
			e.printStackTrace();
			
		} 
		
		// check if contender data is filled
		// get all contenders for events that are completed
		Set<Contender> contenders = new HashSet<>();
		for(BettableEvent event : events) {
			for(Contender contender : event.getContenders()) {
				contenders.add(contender);
				System.out.println(contender);
			}
		}
		
		System.out.println("--------------------");
		// get all wagers
		jpql = "Select w FROM Wager w";
		List<Wager> wagers = null;
		try {
			wagers = em.createQuery(jpql, Wager.class).getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("----------------");
		System.out.println(wagers.size());		
		// Filter wagers
		List<Wager> closedWagers = new ArrayList<>();
		for(Wager wager : wagers) {
			if(contenders.contains(wager.getContender())){
				closedWagers.add(wager);
			}
		}
		
		System.out.println(closedWagers.size());
		
		
		System.out.println("--------");
		Map<Integer, CalculatedWinnings> results = new HashMap<>();
		for(Wager wager : closedWagers) {
			int userId = wager.getUser().getId();
			CalculatedWinnings cw = null;
			if(!results.containsKey(userId)) {
				cw = new CalculatedWinnings(wager.getUser(), 0, 0);
				results.put(userId,cw);
			}
			
			cw = results.get(userId);
			
			double payout = (1 / (wager.getContender().getOdds() / 100));
			System.out.println(wager.getUser().getUsername() + " bet " + wager.getBetAmount() + " on " + wager.getContender().getName());
			if(wager.getContender().isWinner()) {
				cw.setCount(cw.getCount() + 1);
				cw.setTotal(cw.getTotal() + payout + wager.getBetAmount());
				System.out.println(wager.getUser().getUsername() + " won " + (payout + wager.getBetAmount()));
			}
			else {
				cw.setCount(cw.getCount() - 1);				
				cw.setTotal(cw.getTotal() - wager.getBetAmount());
				System.out.println(wager.getUser().getUsername() + " lost " + wager.getBetAmount());
			}
		}
		

		return results;
	}

	@Override
	public List<Wager> getWagersForEvent(int eventId) {
		List<Wager> wagers = null;
		String jpql = "SELECT w FROM Wager w";
		try {
			wagers = em.createQuery(jpql, Wager.class).getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		List<Wager> res = new ArrayList<>();
		for(Wager wager : wagers) {
			if(wager.getContender().getEvent().getId() == eventId) {
				res.add(wager);
			}
		}
		
		return res;
	}

	
	

	


	@Override
	public Contender findContenderById(int contenderId) {
		return em.find(Contender.class, contenderId);
		
	}

	@Override
	public void displayUserWinLossRatio(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EventComment> getEventComments(int eventId) {
		List<EventComment> comments = null;
		String jpql = "Select w FROM EventComment w WHERE w.bettableEvent.id = :eventId";
		try {
			comments = em.createQuery(jpql, EventComment.class)
					.setParameter("eventId", eventId)
					.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return comments;
	}

	@Override
	public void getPayoutResultsForEvent(int event_id) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Transactional
	@Override
	public void updateExpiredEvents() {
		  TypedQuery<BettableEvent> query = em.createQuery("SELECT e FROM BettableEvent e", BettableEvent.class);
		  List<BettableEvent> events = query.getResultList();
		  LocalDateTime currentTime = LocalDateTime.now();
		  for(BettableEvent event : events) {
			  LocalDateTime expirationDate = event.getEndDate();
			  if(currentTime.isAfter(expirationDate)) {
				  event.setActive(false);
			  }
		  }
	}
	
}
	

	
	
	
	
	
	
	
	
	
	


