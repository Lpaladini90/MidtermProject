package com.skilldistillery.betroyaleapp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.betroyaleapp.entities.BettableEvent;
import com.skilldistillery.betroyaleapp.entities.Category;
import com.skilldistillery.betroyaleapp.entities.Contender;
import com.skilldistillery.betroyaleapp.entities.Subcategory;
import com.skilldistillery.betroyaleapp.entities.User;
import com.skilldistillery.betroyaleapp.entities.Wager;

@Service

public class UserDaoImpl implements UserDAO {

	private Map<Integer, User> users;

	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
		User user = dao.findById(1);
		System.out.println(user);
	}

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public User findById(int userId) {
		return em.find(User.class, userId);
	}

	@Transactional
	@Override
	public User searchByUsername(String username) {
		User user = null;

		String jpql = "SELECT u FROM User u where u.username = :username";
		try {
			user = em.createQuery(jpql, User.class).setParameter("username", username).getSingleResult();

		} catch (Exception e) {
		}
		return user;
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
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setActive(user.getActive());
		updatedUser.setRole(user.getRole());
		updatedUser.setProfileImage(user.getProfileImage());
		updatedUser.setAboutMe(user.getAboutMe());

		em.persist(updatedUser);
		em.flush();
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
	public Wager createWager(Wager wager, int userId, int contenderId) {
		User user = em.find(User.class, userId);
		wager.setUser(user);
		wager.setBetAmount(wager.getBetAmount());
		Contender contender = em.find(Contender.class, contenderId);
		wager.setContender(contender);
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
			double odds = wager.getContender().getOdds();
			if (wager.getContender().isWinner()) {
				double winnings = (1 / (odds / 100));
				count++;
				total += winnings;
			}
		}
		//CalculatedWinnings winnings = new CalculatedWinnings(wager., count, total);

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

	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<User>  display_List_Of_User_Bet_Info_In_A_BettableEvent(int userId) {
		List<User> users= new ArrayList<User>();
		
		try {
			
			
			
			
		}catch(Exception e){
		return users;
		
		}
		
		
		return users;
		
	}
	
	
	
	
	
	// comments as lines
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
