<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	 <nav class="navbar navbar-expand-lg navbar-dark bg-dark py-3 fixed-top">
        <a class="navbar-brand" href="#">BetRoyale</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav ms-auto mx-3 mt-2 mt-lg-0">
                <li class="nav-item active"><a class="nav-link" href="goHome.do">Home
                        <span class="sr-only">(current)</span>
                    </a></li>
                    
                    
                <c:if test="${empty user }" >
                
                <li class="nav-item"> <a class="nav-link" href="#" data-toggle="modal" data-target="#at-login">Login</a></li>
                
                </c:if>
                
                <c:if test="${not empty user}">
                <li class="nav-item"> <a class="nav-link" href="accountHome.do?userId=${user.id}">Profile</a></li>
                <li class="nav-item"> <a class="nav-link" href="loadEvents.do?userId=${user.id}">View Events</a></li>
                <li class="nav-item"> <a class="nav-link" href="logout.do">Logout</a></li>
                
                </c:if>
              

            </ul>
        </div>
    </nav>
	
	
	
	<section class="at-login-form">

        <!-- MODAL LOGIN -->
        <div class="modal fade" id="at-login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    	<div class="display-5">Login</div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                       
                        <form  action="login.do" method="POST" >
                            <div class="form-group">
                                <input type="text" class="form-control-form " id="username" name="username"
                                    placeholder="Enter Your Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control-form " id="password" name="password"
                                    placeholder="Enter Your Password">
                            </div>

                            <button type="submit" class="btn-lgin">Login</button>
                                                        <div class="row">
  
                                <div class="col-md-4 col-md-offset-2">
                                    <p class="" data-toggle="modal" data-dismiss="modal"
                                        data-target="#at-reset-pswd"> Forgot Password</p>
                                </div>
                            </div>
                            
                            
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-md-4 col-md-offset-2">
                                <button class="btn btn-outline-primary" data-toggle="modal" data-dismiss="modal"
                                    data-target="#at-signup-filling">Sign Up </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- MODAL LOGIN ENDS -->


        <!-- MODAL SIGNUP FORM FILLING -->
        <div class="modal fade" id="at-signup-filling" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                    	<div class="display-5">Sign up</div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>

                    <div class="modal-body">
                    
                        <form action="createUser.do" method="POST">
                            <div class="form-group">
                                <input type="text" class="form-control-form " id="firstName" name="firstName"
                                    placeholder="First Name">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control-form " id="lastName" name="lastName"
                                    placeholder="Last Name">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control-form " id="email" name="email"
                                    placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control-form " id="username" name="username"
                                    placeholder="Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control-form " id="password" name="password"
                                    placeholder="Password">
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">
                                            I’d like to receive updates on My Betting Events.
                                        </label>
                                    </div>

                                </div>
                            </div>
                            <button type="submit" class="btn-lgin">Signup</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-md-6">
                                <p class="ta-l">Already a Member? </p>
                            </div>
                            <div class="col-md-4 col-md-offset-2">
                                <button class="btn-gst" data-toggle="modal" data-dismiss="modal"
                                    data-target="#at-login">Login</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- MODAL SIGNUP FORM FILLING -->
        <!-- MODAL FORGOT PASSWORD -->
        <div class="modal fade" id="at-reset-pswd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <p> Enter the email address associated with your account, and we will email you a link to
                                reset your password. </p>
                            <div class="form-group">

                                <input type="email" class="form-control-form " id="exampleInputEmaillog"
                                    placeholder="Email">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </div>
        <!-- MODAL HELP -->
        <div class="modal fade" id="at-helping" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <input type="text" class="form-control-form " id="exampleInputEmaillog"
                                    placeholder="Enter Your Searches ">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <button class="btn-gst" data-toggle="modal" data-dismiss="modal">Send a reset Link</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    </section>
	
