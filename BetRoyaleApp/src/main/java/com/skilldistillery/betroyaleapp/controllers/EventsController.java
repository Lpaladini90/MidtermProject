package com.skilldistillery.betroyaleapp.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.betroyaleapp.data.CalculatedWinnings;
import com.skilldistillery.betroyaleapp.data.EventsDAO;
import com.skilldistillery.betroyaleapp.entities.BettableEvent;
import com.skilldistillery.betroyaleapp.entities.Contender;
import com.skilldistillery.betroyaleapp.entities.EventComment;
import com.skilldistillery.betroyaleapp.entities.User;
import com.skilldistillery.betroyaleapp.entities.Wager;

@Controller
public class EventsController {

	@Autowired
	private EventsDAO dao;

	@GetMapping(path = {"allBetEvents.do"})
	public ModelAndView displayAllBettableEvents() {
		ModelAndView mv = new ModelAndView();
		List<BettableEvent> events = dao.displayBettableEvents();
		mv.addObject("betEvent", events);
		mv.setViewName("home");
		 
		return mv;
		
	} 
	
	@GetMapping(path = {"activeBets.do"})
	public ModelAndView displayActiveBettableEvents() {
		ModelAndView mv = new ModelAndView();
		List<BettableEvent> events = dao.displayActiveBettableEvents();
		mv.addObject("activeBetEvent", events);
		mv.setViewName("home");
		 
		return mv;
		
	}
	
	@GetMapping(path = {"expiredBets.do"})
	public ModelAndView displayExpiredBettableEvents() {
		ModelAndView mv = new ModelAndView();
		List<BettableEvent> events = dao.displayExpiredBettableEvents();
		mv.addObject("expiredBetEvents", events);
		mv.setViewName("home");
		
		return mv;
		
	}
	
	@RequestMapping(path="addComment.do")
	public ModelAndView addComment(EventComment comment, int userId, int eventId) {
		comment.setCommentDate(LocalDateTime.now());
		BettableEvent event = dao.findEventById(eventId);
		User user = dao.findUserById(userId);
		comment.setBettableEvent(event);
		comment.setUser(user);
		System.out.println(comment);
		System.out.println(userId);
		System.out.println(eventId);
		
		ModelAndView mv = new ModelAndView();
		comment = dao.addComment(comment);
		System.out.println(comment);
		
		// TODO: add reply to comment func
		
		mv.setViewName("home");
		return mv;
	}
	
	
	@GetMapping(path = {"displayUserCreatedEvents.do"})
	public ModelAndView displayUserCreatedBettableEvents(int userId) {
		ModelAndView mv = new ModelAndView();
		User user=dao.findUserById(userId);
		System.out.println(user);
		List<BettableEvent> userCreatedEvents = dao.displayUserCreatedBettableEvents(userId);
		System.out.println(userCreatedEvents);
		mv.addObject(user);
		mv.addObject("userEvents", userCreatedEvents);
		mv.setViewName("accounthome");
		
		return mv;
		
	}
	
	
	
	//TODO- change this to a post mapping method	
	
	@GetMapping("updateBetEvent.do")
	public ModelAndView updateBettableEvent(int winningId, int eventId) {
		BettableEvent event = dao.findEventById(eventId);
		System.out.println(event);
		List<Contender> contenders = event.getContenders();
		for (Contender contender : contenders) {
			boolean isWinner = (winningId == contender.getId());
			Contender newContender = dao.updateContender(contender.getId(), isWinner);
		
			System.out.println(newContender);
			
		}
		
		event.setActive(false);
		event.setCompletion(true);
		dao.updateBettableEvent(event);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		
		return mv;
		
	}
	
	
	@GetMapping("getLeaderboard.do")
	public ModelAndView getLeaderBoard(){
		ModelAndView mv = new ModelAndView();
		Map<Integer, CalculatedWinnings> results = dao.calculateLeaderBoard();
		System.out.println("-------------------------");
		for(int key : results.keySet()) {
			User user = results.get(key).getUser();
			double numberOfTimeCorrect = results.get(key).getCount();
			double totalProfit = results.get(key).getTotal();
			System.out.printf("%s wins: %.2f profit: %.2f\n", user.getUsername(), numberOfTimeCorrect, totalProfit);
		}
		mv.setViewName("home");
		return mv;
	}
	
	@GetMapping("getWagersByEventId.do")
	public ModelAndView getWagersForEvent(int eventId) {
		ModelAndView mv = new ModelAndView();
		List<Wager> wagers = dao.getWagersForEvent(eventId);

		
		mv.setViewName("accounthome");
		return mv;
	}
	
	
	
	
	
	
	
	
}
