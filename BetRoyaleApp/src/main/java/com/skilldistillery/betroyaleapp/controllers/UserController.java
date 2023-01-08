package com.skilldistillery.betroyaleapp.controllers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.betroyaleapp.data.CalculatedWinnings;
import com.skilldistillery.betroyaleapp.data.EventsDAO;
import com.skilldistillery.betroyaleapp.data.UserDAO;
import com.skilldistillery.betroyaleapp.entities.BettableEvent;
import com.skilldistillery.betroyaleapp.entities.Category;
import com.skilldistillery.betroyaleapp.entities.Contender;
import com.skilldistillery.betroyaleapp.entities.Subcategory;
import com.skilldistillery.betroyaleapp.entities.User;
import com.skilldistillery.betroyaleapp.entities.Wager;

@Controller
public class UserController {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private EventsDAO eventDao;

	@RequestMapping(path= "goHome.do")
	public ModelAndView goHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}

	@RequestMapping(path= "accountHome.do")
	public ModelAndView goToAccountHome(Integer userId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = userDao.findById(userId);
		session.setAttribute("user", user);
		mv.addObject("user", user);
		mv.setViewName("accounthome");
		return mv;
	}
		
	@RequestMapping(path ="splash.do")
	public ModelAndView splash() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("splashPage");
		return mv;
	}
	
	@PostMapping(path = "createUser.do")
	public ModelAndView home(User user) {
		User newUser = userDao.createUser(user);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", newUser);
		mv.setViewName("accounthome");
		return mv;
	}

	@PostMapping(path = "updateUser.do")
	public String updateUser(Model model, User user) {
		User updatedUser = userDao.updateUser(user);
		model.addAttribute(updatedUser);
		return "accounthome";
	}
	
//	--------------------------WAGER CRUD-------------------------------
	
	@PostMapping(path = "createWager.do")
	public String createWager(int userId, int contenderId, double betAmount, RedirectAttributes redirectAttrs) {
		Wager wager = new Wager();
		wager.setBetAmount(betAmount);
		wager = userDao.createWager(wager, userId, contenderId);
		redirectAttrs.addAttribute("userId", userId);
		int eventId = wager.getContender().getEvent().getId();
		redirectAttrs.addAttribute("eventId", eventId);
		return "redirect:/loadEventPage.do";
	}

	@GetMapping("getWagersForEvent.do")
	public ModelAndView getWagersForEvent(int eventId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("accounthome");
		return mv;
	}
	

	@GetMapping(path = "loadWager.do")
	public ModelAndView loadWagerPage(int userId, int eventId) {
		ModelAndView mv = new ModelAndView();
		Wager wager = userDao.findWagerById(userId);
		User user = userDao.findById(userId);
		BettableEvent event = eventDao.findEventById(eventId);
		mv.addObject("event", event);
		mv.addObject("user", user);
		mv.addObject("wager", wager);
		mv.setViewName("accounthome");
		return mv;
	}

	@GetMapping(path = "getWagers.do")
	public ModelAndView getWager(int userId) {
		ModelAndView mv = new ModelAndView();
		User user = userDao.findById(userId);
		List<Wager> wagers = userDao.getWagers(userId);
		List<BettableEvent> events = eventDao.displayBettableEvents();
		mv.addObject("events", events);
		mv.addObject("wager", wagers);
		mv.addObject("user", user);
		mv.setViewName("wagersView");
		return mv;
		
	}

	@PostMapping(path = "updateWager.do")
	public String updateWager(Model model, Wager wager) {
		Wager updatedWager = userDao.updateWager(wager);
		model.addAttribute(updatedWager);
		return "accounthome";
	}
	
//	--------------------------BETTING EVENT CRUD-------------------------------
	//TODO: change to command object
	@RequestMapping(path = "userCreateBetEvent.do", method = RequestMethod.POST)
	public ModelAndView userCreateBetEvent(BettableEvent event, int userId, String endDate2, String[] contenderName,
			Double[] contenderOdds, String[] cname, String categoryString, String categorydescription) {
		ModelAndView mv = new ModelAndView();
		System.out.println("********");
		System.out.println(event);
		if (userId > 0) {
			String[] data = endDate2.split("-");
			int year = Integer.parseInt(data[0]);
			int month = Integer.parseInt(data[1]);
			int day = Integer.parseInt(data[2]);
			LocalDateTime eventEndDate = LocalDateTime.of(year, month, day, 0, 0, 0);

			event.setEndDate(eventEndDate);
			BettableEvent newEvent = userDao.createBettableEvent(event, userId);

			// ---- Link Contenders to categories
			if (newEvent.getId() != 0) {

				if (contenderName.length == contenderOdds.length) {
					for (int i = 0; i < contenderName.length; ++i) {
						Contender contender = new Contender();
						contender.setName(contenderName[i]);
						contender.setOdds(contenderOdds[i]);
						contender.setEvent(newEvent);
						userDao.createContender(contender);
					}
				}
			}


			// Link Category
			Category category = userDao.searchByCategory(categoryString);
			if (category == null || category.getId() == 0) {
				category = new Category();
				category.setName(categoryString);
				category.setDescription(categorydescription);
				category = userDao.createCategory(category);
			}

			// ----- link subcategories to -------
			if (cname.length > 0) {
				for (int i = 0; i < cname.length; ++i) {
					Subcategory subcategory = new Subcategory();
					subcategory.setName(cname[i]);
					subcategory.setDescription("");
					subcategory.setCategory(category);
					subcategory = userDao.createSubCategory(subcategory);
					event.addSubcategory(subcategory);
				}
			}

			mv.addObject("event", newEvent);
			mv.setViewName("accounthome");
			return mv;
		}

		else {
			mv.setViewName("home");
			return mv;
		} 

	}

//	------------------------ LOGIN/LOGOUT -----------------------------
	
	
	@RequestMapping("login.do")
	public ModelAndView login(User user, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		user = new User();
		if(session.getAttribute("user") != null) {
			mv.setViewName("redirect:home.do");
		}
		else {
			mv.addObject("userLogin", user);
			mv.setViewName("login");
		}
		return mv;
		
	}

	@RequestMapping(path = "login.do", method = RequestMethod.POST)
	public ModelAndView submitLogin(String username, String password, HttpSession session) {

		ModelAndView mv = new ModelAndView();
		User user = userDao.login(username, password);
		
		if(user == null) {
			mv.setViewName("home");
			return mv;
		}
		
		session.setAttribute("user", user);
		mv.addObject("user", user);
		List<Wager> wagers = userDao.getWagers(user.getId());		
		mv.addObject("userWagers",wagers);
		
		List<CalculatedWinnings> results = userDao.calculateLeaderBoard();
		Collections.sort(results,  (r1, r2) ->  (int)r2.getCount() - (int)r1.getCount());
		
		for(int rank = 1; rank < results.size(); ++rank) {
			CalculatedWinnings cw = results.get(rank - 1);
			cw.setRank(rank);
		}
		
		mv.addObject("rankings", results);
		mv.setViewName("accounthome");
		return mv;
	}

	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:goHome.do";
	}

//	----------------------- SEARCH -----------------------------------	
	@GetMapping("search.do")
	public ModelAndView findByUsername(String username) {
		ModelAndView mv = new ModelAndView();
		User newUser = userDao.searchByUsername(username);
		mv.addObject("username", newUser);
		mv.setViewName("home");
		return mv;
	}

	@GetMapping("leaderboard.do")
	public ModelAndView getLeaderboard(int userId) {
		ModelAndView mv = new ModelAndView();
		CalculatedWinnings winnings = userDao.getWinnings(userId);
		mv.addObject("winnings", winnings);
		mv.setViewName("home");
		return mv;
	}
}
