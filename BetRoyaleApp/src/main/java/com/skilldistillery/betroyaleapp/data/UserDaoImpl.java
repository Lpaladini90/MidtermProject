package com.skilldistillery.betroyaleapp.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.skilldistillery.betroyaleapp.entities.BettableEvent;
import com.skilldistillery.betroyaleapp.entities.Category;
import com.skilldistillery.betroyaleapp.entities.Contender;
import com.skilldistillery.betroyaleapp.entities.Subcategory;
import com.skilldistillery.betroyaleapp.entities.User;
import com.skilldistillery.betroyaleapp.entities.Wager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDaoImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public User findById(int userId) {
		return em.find(User.class, userId);
	}

	
	@Override
	@Transactional(readOnly = true)
	public User searchByUsername(String username) {

		try {
			String jpql = "SELECT u FROM User u where u.username = :username";
			User user = em.createQuery(jpql, User.class).setParameter("username", username).getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
		
	}

	@Transactional
	@Override
	public User createUser(User user) {
		em.persist(user);
		em.flush();
		return user;
	}

	@Transactional
	@Override
	public User updateUser(User user) {
		User updatedUser = findById(user.getId());
		updatedUser.setUsername(user.getUsername());
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setActive(user.getActive());
		updatedUser.setRole(user.getRole());
		updatedUser.setProfileImage(user.getProfileImage());
		updatedUser.setAboutMe(user.getAboutMe());
		return updatedUser;
	}

	@Transactional
	@Override
	public BettableEvent createBettableEvent(BettableEvent event, int userId) {
		User user = em.find(User.class, userId);
		event.setUser(user);
		em.persist(event);
		em.flush();
		return event;
	}

	@Transactional
	@Override
	public Contender createContender(Contender contender) {
		em.persist(contender);
		em.flush();
		return contender;
	}

	@Transactional
	@Override
	public User login(String username, String password) {

		User user = null;

		String jpql = "SELECT u FROM User u where u.username = :username";
		try {
			user = em.createQuery(jpql, User.class).setParameter("username", username).getSingleResult();

			if (user != null) {
				if (user.getPassword().equals(password)) {
					return user;
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	@Transactional
	@Override
	public Wager findWagerById(int wagerId) {
		return em.find(Wager.class, wagerId);
	}

	@Transactional
	@Override
	public Wager updateWager(Wager wager) {
		em.find(Wager.class, wager.getId());
		Wager updatedWager = findWagerById(wager.getId());
		updatedWager.setBetAmount(wager.getBetAmount());
		updatedWager.setContender(wager.getContender());
		updatedWager.setMultiplier(wager.getMultiplier());
		updatedWager.setContender(wager.getContender());

		em.persist(updatedWager);
		em.flush();
		return updatedWager;
	}

	@Transactional
	@Override
	public Wager createWager(Wager wager, int userId, int contenderId) {
		User user = em.find(User.class, userId);
		Contender contender = em.find(Contender.class, contenderId);
		double multiplier = 1 /contender.getOdds();
		
		wager.setUser(user);
		wager.setBetAmount(wager.getBetAmount());
		wager.setContender(contender);
		wager.setMultiplier(multiplier);
		em.persist(wager);
		em.flush();
		return wager;


	}

	@Transactional
	@Override
	public Wager showWager(Wager wager, int userId) {
		User user = em.find(User.class, userId);
		return wager;

	}

	@Override
	public List<Wager> getWagers(int userId) {
		List<Wager> wagers = new ArrayList<Wager>();

		String jpql;
		try {
			jpql = "SELECT w FROM Wager w where w.user.id = :id";
			wagers = em.createQuery(jpql, Wager.class).setParameter("id", userId).getResultList();

		} catch (Exception e) {
			return wagers;
		}

		return wagers;
	}

	@Override
	public CalculatedWinnings getWinnings(int userId) {
		List<Wager> wagers = new ArrayList<Wager>();

		String jpql;
		try {
			jpql = "SELECT w FROM Wager w where w.user.id = :id";
			wagers = em.createQuery(jpql, Wager.class).setParameter("id", userId).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		double count = 0;
		double total = 0;
		for (Wager wager : wagers) {
			if (wager.getContender().isWinner()) {
				double odds = wager.getContender().getOdds();
				double winnings = (1 / (odds / 100));
				count++;
				total += winnings;
			}
		}

		return null;
	}

	@Transactional
	@Override
	public Category searchByCategory(String keyword) {
		Category category = null;
		String jpql = "SELECT c FROM Category c WHERE c.name = :name";
		try {
			category = em.createQuery(jpql, Category.class).setParameter("name", keyword).getSingleResult();

		} catch (Exception e) {
		}

		return category;
	}

	@Transactional
	@Override
	public Category createCategory(Category category) {
		em.persist(category);
		em.flush();
		return category;
	}

	@Transactional
	@Override
	public Subcategory createSubCategory(Subcategory sb) {
		em.persist(sb);
		em.flush();
		return sb;
	}


	public List<User> display_List_Of_User_Bet_Info_In_A_BettableEvent(int userId) {
		List<User> users = new ArrayList<User>();

		try {

		} catch (Exception e) {
			return users;

		}

		return users;

	}

	@Override
	public Contender findContenderById(int contenderId) {
		return em.find(Contender.class, contenderId);
	}

	@Override
	public List<CalculatedWinnings> calculateLeaderBoard() {
		List<Object[]> res = null;
		String jpql = "SELECT w.user.id, w.betAmount, w.multiplier, w.contender.isWinner, w.contender.odds "
				+ "FROM Wager w where w.contender.event.completion = true";
		 
		try {
			res = em.createQuery(jpql).getResultList();
		}catch(Exception e){ 
			e.printStackTrace();
		} 
		
		Map<Integer, CalculatedWinnings> map = new HashMap<>();
		for(int i = 0; i < res.size(); ++i){
			Object[] row = res.get(i);
			int userId = (int) row[0];
			double amount = (double) row[1];
			boolean isWinner = (boolean) row[3];
			double odds = (double)row[4];
			double payout = (1 / (odds / 100));
			
			if(!map.containsKey(userId)) {
				User user = em.find(User.class, userId);
				map.put(userId, new CalculatedWinnings(user, 0, 0));
			}
			
			
			if(isWinner) {
				map.get(userId).setCount(map.get(userId).getCount() + 1);
				map.get(userId).setTotal(map.get(userId).getTotal() + payout + amount);	
			}
			else {
				map.get(userId).setCount(map.get(userId).getCount() - 1);				
				map.get(userId).setTotal(map.get(userId).getTotal() - amount);
			}	
		}
		
		List<CalculatedWinnings> finalResults = new ArrayList<>(map.values());
		
		Collections.sort(finalResults,  (a,b) ->  {
			if(((int)b.getCount() - (int)a.getCount()) == 0) 
				return (int)b.getTotal() - (int)a.getTotal();
			else 
				return (int)b.getCount() - (int)a.getCount();
		});
		
		return finalResults;
	}
}





















//MEOW