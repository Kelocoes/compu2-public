<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="com.example.demo.HolaMundo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>JSP - Hello World</title>
  </head>
  <body>
    <h1><%= "Hello World!" %></h1>
    <br/>
    <a href="hello-servlet">Hello Servlet</a>
  <%
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    HolaMundo obj1 = (HolaMundo) context.getBean("HolaMundo");
  %>
  <p><%= obj1.getMessage() %></p>
  </body>
</html>