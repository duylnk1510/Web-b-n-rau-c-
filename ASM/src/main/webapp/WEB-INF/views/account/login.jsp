<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<link rel="stylesheet" href="/fonts/material-design-iconic-font.min.css" type="text/css">

<link rel="stylesheet" href="/css/styleLR.css" type="text/css">
<body>
	
	<div class="main">
	
        <!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
     
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="/img/signin-image.jpg" alt="sing up image"></figure>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="POST" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="your_name"></label>
                                <input type="text" name="username" id="your_name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="your_pass"></label>
                                <input type="password" name="password" id="your_pass" placeholder="Password"/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox" ${ch} name="remember" value="true" id="remember-me" class="agree-term" />
                                <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                            </div>
                            <b><i>${message}${param.error}</i></b>
                        </form>
                        <span style="margin-top: 20px; display: block;">Not a remember? <a href="/account/regis" class="signup-image-link" style="display: inline-block;" >Create an account</a></span>
                    </div>
                </div>
            </div>
        </section>

    </div>
	
	<script src="/js/jquery-3.3.1.min.js"></script>
</body>
</html>