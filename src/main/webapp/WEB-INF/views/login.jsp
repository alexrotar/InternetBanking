<%-- 
    Document   : newjsp
    Created on : Dec 13, 2013, 11:28:12 AM
    Author     : orotar
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.1.1/css/bootstrap.min.css">
        <title>Login</title>
    </head>
    <body>
        <h1>Enter your credentials</h1>
        <form action="j_spring_security_check" method="POST" >
           <div class="table-responsive">
            <table>
                <tr>
                    <td>Username:</td><td><input type="text" name="j_username" placeholder="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td><td><input type="password" name="j_password" placeholder="password"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="right"><input type="submit" value="Login" class="btn btn-primary"/></td>
                </tr>
            </table>
           </div>
        </form>
        <font color="red">
		<span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"]}</span>
	</font>
    </body>
</html>
