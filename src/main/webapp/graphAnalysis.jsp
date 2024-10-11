
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
    <style>
        button {
            padding: 10px 20px;
            text-align: center;
            margin: 4px 2px;
        }
        button {
            margin-bottom: 40px;
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Graph Analysis<span style="float: right;">Jash Shah</span></h1>
        <form method="POST" action="/index.jsp" style="margin-left: 20px">
            <button type="submit" style="margin-bottom: 10px; padding: 6px 10px; float: left;">< Go Back to Homepage</button>
        </form>
    </div>
</header>
<div>
    <nav>
        <form method="GET" action="/ageGraph.html">
            <button type="submit">Age Distribution Graph</button>
        </form>
        <form method="GET" action="/raceGraph.html">
            <button type="submit">Race Distribution Graph</button>
        </form>
        <form method="POST" action="/ethnicityCriteria.jsp">
            <button type="submit">Ethnicity Distribution Graph</button>
        </form>
        <form method="GET" action="/genderGraph.html">
            <button type="submit">Gender Distribution Graph</button>
        </form>
        <form method="GET" action="/maritalGraph.html">
            <button type="submit">Marital Distribution Graph</button>
        </form>
    </nav>
</div>
</body>
</html>