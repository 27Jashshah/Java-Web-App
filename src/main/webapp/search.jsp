
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<header>
    <div class="container">
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Search Patient By Keyword<span style="float: right;">Jash Shah</span></h1>
        <form method="POST" action="/index.jsp" style="margin-left: 20px">
            <button type="submit" style="margin-bottom: 10px; padding: 6px 10px; float: left;">< Go Back to Homepage</button>
        </form>
    </div>
</header>
<div class="main">
    <form method="POST" action="/runsearch.html">
        <input type="text" name="searchstring" placeholder="Enter search keyword here" style=" width: 250px; margin-top: 20px"/>
        <input type="text" name="searchcolumn" placeholder="Enter column here" style=" width: 250px; margin-top: 20px"/>
        <button type="submit" style="margin-top: 20px; margin-left: 20px; padding: 6px 10px; float: left;">Search</button>
    </form>
</div>
</body>
</html>

