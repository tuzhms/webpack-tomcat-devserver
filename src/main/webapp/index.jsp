<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="jsp.experiments.App" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<h1>Это стартовая страница примера</h1>
<nav style="text-align: center;">
    <a href="fresh/Page1.html">Page 1</a>
    <a href="fresh/Page2.html">Page 2</a>
    <a href="fresh/a/Page3.html">Page 3</a>
    <a href="hello.jsp">Hello</a>
</nav>
<p style="color: blueviolet; margin: 2rem 0;">
    <%= new App().getGreeting() %>
</p>
</html>