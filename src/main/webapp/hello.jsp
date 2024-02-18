<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>Hello, ${param.name == null ? "JSP" : param.name}</h1>
    <a href="index.jsp">Index</a>
    <jsp:include page="foo.jsp"/>
    <script>
        const a = "hello"

        const b = () => console.log("hello")
    </script>

    <jsp:include page="Input.jsp?value=&placeholder=some_text"/>
</body>
</html>