<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
    <form action="createWager.do" method="POST">
	
		 <br> <br>
	<label for="betAmount">Bet Amount: </label> 
	<input id="betAmount"
		type="text" placeholder="Bet Amount" name="betAmount" />
		 <br> <br>
	
	 <input type="submit" />
</form>