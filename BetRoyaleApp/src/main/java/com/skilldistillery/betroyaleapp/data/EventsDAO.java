package com.skilldistillery.betroyaleapp.data;

import java.util.List;
import java.util.Map;

import com.skilldistillery.betroyaleapp.entities.BettableEvent;
import com.skilldistillery.betroyaleapp.entities.Contender;
import com.skilldistillery.betroyaleapp.entities.EventComment;
import com.skilldistillery.betroyaleapp.entities.User;
import com.skilldistillery.betroyaleapp.entities.Wager;

public interface EventsDAO {

	List<BettableEvent> displayBettableEvents();
	
	EventComment addComment(EventComment comment);
	BettableEvent findEventById(int id);
	User findUserById(int id);

	List<BettableEvent> displayActiveBettableEvents();
	
	List<BettableEvent> displayExpiredBettableEvents();


	List<BettableEvent> displayUserCreatedBettableEvents(int userId);

	 
	Contender updateContender(int contenderId, boolean isWinner);
	
	BettableEvent updateBettableEvent(BettableEvent event);
	
	Map<Integer, CalculatedWinnings> calculateLeaderBoard();
	
	List<Wager> getWagersForEvent(int eventId);


	Contender findContenderById(int contenderId);


	public void displayUserWinLossRatio(int userId);

	List<EventComment> getEventComments(int eventId);


	void getPayoutResultsForEvent(int event_id);

	void updateExpiredEvents();

}
