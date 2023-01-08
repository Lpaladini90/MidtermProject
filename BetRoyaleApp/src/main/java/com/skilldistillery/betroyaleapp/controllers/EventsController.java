package com.skilldistillery.betroyaleapp.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import com.skilldistillery.betroyaleapp.data.CalculatedWinnings;
import com.skilldistillery.betroyaleapp.data.EventsDAO;
import com.skilldistillery.betroyaleapp.entities.BettableEvent;
import com.skilldistillery.betroyaleapp.entities.Contender;
import com.skilldistillery.betroyaleapp.entities.EventComment;
import com.skilldistillery.betroyaleapp.entities.Subcategory;
import com.skilldistillery.betroyaleapp.entities.User;
import com.skilldistillery.betroyaleapp.entities.Wager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@EnableScheduling
public class EventsController {

	@Autowired
	private EventsDAO dao;
	
	  @PostConstruct
	  public void init() {
		  updateEvents();  // execute the method on start up
	  }
	
	
	@GetMapping("editEvent.do")
	public String goToEditPage(Model model, int eventId) {
		BettableEvent event = dao.findEventById(eventId);
		model.addAttribute("event", event);
		return "editevent";
	}
	

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
	public String addComment(EventComment comment, int userId, int eventId,RedirectAttributes redirectAttrs) {
		comment.setCommentDate(LocalDateTime.now());
		BettableEvent event = dao.findEventById(eventId);
		User user = dao.findUserById(userId);
		comment.setBettableEvent(event);
		comment.setUser(user);
		
		comment = dao.addComment(comment);
		redirectAttrs.addAttribute("userId", userId);
		redirectAttrs.addAttribute("eventId", eventId);

		return "redirect:/loadEventPage.do";
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
	
	
	
	
	@PostMapping("updateBetEvent.do")
	public String updateBettableEvent(Integer [] contenderResult, Integer[] contenderIds, 
			Integer eventId,RedirectAttributes redirectAttrs,HttpSession session) {
		
		User user = (User)session.getAttribute("user");
		System.out.println("Session User: " + user);
		ModelAndView mv = new ModelAndView();
		BettableEvent event = dao.findEventById(eventId);
		System.out.println(event);
		
		List<Contender> contenders = event.getContenders();
		
		for (int i = 0; i < contenderIds.length; i++) {
			boolean isWinner = contenderResult[i] != 0;
			dao.updateContender(contenderIds[i], isWinner);
		}
		
		event.setActive(false);
		event.setCompletion(true);
		dao.updateBettableEvent(event);

		redirectAttrs.addAttribute("userId", user.getId());
		redirectAttrs.addAttribute("eventId", eventId);
		return "redirect:/loadEventPage.do";
		
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
	
	@GetMapping("profiledisplay.do")
	public ModelAndView userWinLoss(int userId) {
		ModelAndView mv = new ModelAndView();
		Map<Integer, CalculatedWinnings> results = dao.calculateLeaderBoard();
		CalculatedWinnings cw = results.get(userId);
		mv.addObject("wrl",cw);
		mv.setViewName("profiledisplay");
		return mv;
	}
	
	@GetMapping("loadEvents.do")
	public ModelAndView loadEvents(int userId) {
		ModelAndView mv = new ModelAndView();
		List<BettableEvent> events = dao.displayBettableEvents();
		for(BettableEvent be : events) {
			List<Subcategory> sub = be.getSubcategories();
			System.out.println(sub);
		}
		
		mv.addObject("events", events);
		mv.addObject("userId", userId);
		// if this doesnt work change back to eventsView
		mv.setViewName("vieweventsinrow");
		return mv;
	}
	
	
	@GetMapping("loadEventPage.do")
	public ModelAndView goToEventPage(int userId, int eventId) {
		ModelAndView mv = new ModelAndView();
		System.out.println("userid: " + userId + " eventId: " + eventId);
		BettableEvent event = dao.findEventById(eventId);
		User user = dao.findUserById(userId);
		List<Wager> wagers = dao.getWagersForEvent(eventId);
		List<Wager> userWagers = new ArrayList<>();
		
		for(Wager wager : wagers) {
			if(wager.getUser().getId() == userId) {
				userWagers.add(wager);
			}
		}
		
		LocalDateTime closeDate = event.getEndDate();
		System.out.println(closeDate);
		
		List<EventComment> comments = dao.getEventComments(eventId);
		
		mv.addObject("user", user);
		mv.addObject("event", event);
		mv.addObject("eventId", eventId);
		mv.addObject("userId", userId);
		mv.addObject("wagers", wagers);
		mv.addObject("userWagers", userWagers);
		mv.addObject("comments", comments);

		mv.setViewName("updatedEventHome");
		return mv;
		
	}
	
	
	  @Scheduled(cron = "0 0 0 * * *")
	  public void updateEvents() {
		  dao.updateExpiredEvents();
	  }

	
	
	
	
	
}




