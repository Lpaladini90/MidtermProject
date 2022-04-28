<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bet Royale App</title>

<link rel="stylesheet" href="css/navstyles.css">
<link rel="stylesheet" href="css/footer.css">
<jsp:include page="bootstrap"/>
</head>

<body>
	 <nav class="navbar navbar-expand-lg navbar-dark bg-dark py-3 fixed-top">
        <a class="navbar-brand" href="#">BetRoyale</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02"
            aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
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
              
                <li class="nav-item"> <form class="form-inline my-2 my-lg-0" action="search.do" method="GET">
                        <input class="form-control mr-sm-2" type="search" name="username" placeholder="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
	
	
	
	<section class="at-login-form">

        <!-- MODAL LOGIN -->
        <div class="modal fade" id="at-login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <button class="btn-fb"> <i class="fa fa-fw fa-facebook pull-left" aria-hidden="true"></i>
                            Login with Facebook </button> <br>
                        <button class="btn-gp"> <i class="fa fa-fw fa-google-plus pull-left" aria-hidden="true"></i>
                            Login with Google </button> <br>
                        <div class="signup-or-separator">
                            <span class="h6 signup-or-separator-text">or</span>
                            <hr>
                        </div>
                        <form  action="login.do" method="POST" >
                            <div class="form-group">
                                <input type="text" class="form-control-form " id="username" name="username"
                                    placeholder="Enter Your Username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control-form " id="password" name="password"
                                    placeholder="Enter Your Password">
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> Remember me
                                        </label>
                                    </div>
                                </div>
                                <div class="col-md-4 col-md-offset-2">
                                    <p class="frgt-pswd" data-toggle="modal" data-dismiss="modal"
                                        data-target="#at-reset-pswd"> Forgot Password</p>
                                </div>
                            </div>
                            <button type="submit" class="btn-lgin">Login</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-md-6">
                                <p class="inline-block">New User? </p>
                            </div>
                            <div class="col-md-4 col-md-offset-2">
                                <button class="btn-gst" data-toggle="modal" data-dismiss="modal"
                                    data-target="#at-signup">Sign Up </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- MODAL LOGIN ENDS -->

        

        <!-- MODAL SIGNUP -->
        <div class="modal fade" id="at-signup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>
                    <div class="modal-body">
                        <button class="btn-fb"> <i class="fa fa-fw fa-facebook pull-left" aria-hidden="true"></i>
                            Signup with Facebook </button> <br>
                        <button class="btn-gp"> <i class="fa fa-fw fa-google-plus pull-left" aria-hidden="true"></i>
                            Signup with Google </button> <br>
                        <div class="signup-or-separator">
                            <span class="h6 signup-or-separator-text">or</span>
                            <hr>
                        </div>
                        <button type="submit" class="btn-lgin" data-toggle="modal" data-dismiss="modal"
                            data-target="#at-signup-filling">Signup with Email</button>
                        <hr>
                        <p>By signing up, I agree to BetRoyale's: Payments <a href="#">Terms of Service</a>, <a
                                href="#">Privacy Policy</a>, <a href="#">Refund Policy</a>
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
        <div class="modal fade" id="at-signup-filling" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">×</span></button>
                    </div>

                    <div class="modal-body">
                        <p>Sign up with <a href="#">Facebook</a> or <a href="#">Google</a></p>
                        <div class="signup-or-separator">
                            <span class="h6 signup-or-separator--text">or</span>
                            <hr>
                        </div>
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

                                    <p>By signing up, I agree to Bet Royale's , Payments <a href="#">Terms of Service</a>,
                                        <a href="#">Privacy Policy</a>, <a href="#">Guest Refund Policy</a>, and <a
                                            href="#">Host Guarantee Terms</a>. </p>
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
	
	
	
	

		<!-- <nav class="navbar fixed-bottom navbar-expand-md navbar-dark bg-dark">
		<div class="container-fluid">


			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse justify-content-end"
				id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="about.do">About
							Us</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Contact
							Us</a></li>

					<li class="nav-item"><a class="nav-link" href="#">Donate</a></li>
				</ul>

			</div>
		</div>
	</nav> -->
	
	
	
<div class="footer-dark fixed-bottom">
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-2 item">
                    <h3>Contact Us</h3>
                    <ul>
                        <li><a href="#">Requests</a></li>
                        <li><a href="#">Reviews</a></li>
                        <li><a href="#">Contact Form</a></li>
                    </ul>
                </div>
                <div class="col-md-2 item">
                    <h3>Services</h3>
                    <ul>
                        <li><a href="#">Web design</a></li>
                        <li><a href="#">Development</a></li>
                        <li><a href="#">Hosting</a></li>
                    </ul>
                </div>
                <div class="col-md-2 item">
                    <h3>About</h3>
                    <ul>
                        <li><a href="#">Our Team</a></li>
                        <li><a href="#">Company</a></li>
                        <li><a href="#">Careers</a></li>
                    </ul>
                </div>
                <div class="col-md-2 item text">
                    <h3>BetRoyale.com</h3>
                    <p>Browse The Markets. Create Or Pick An Event. Place Your Wager.</p>
                     
                </div>
                
                    <div class="col-md-4 item social"><a href=""><i class="fa fa-facebook"></i></a><a href="#"><i class="fa fa-twitter"></i></a><a href="https://www.youtube.com/channel/UCmqsyauQoYoeipFQ-O3eP3g"><i class="fa fa-youtube"></i></a><a href="#"><i class="fa fa-instagram"></i></a><a href="#"><i class="fa fa-google"></i></a></div>
           
                </div>
                
            <p class="copyright">BetRoyale.com © 2022</p>
        </div>
    </footer>
</div>
	<jsp:include page="myscripts"/>
		</body>
		</html>
		