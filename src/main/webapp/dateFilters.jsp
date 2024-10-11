
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
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Date Filters<span style="float: right;">Jash Shah</span></h1>
        <form method="POST" action="/index.jsp" style="margin-left: 20px">
            <button type="submit" style="margin-bottom: 10px; padding: 6px 10px; float: left;">< Go Back to Homepage</button>
        </form>
    </div>
</header>
<div>
    <nav>
        <form method="POST" action="/oldest.jsp">
            <button type="submit">Oldest Patients</button>
        </form>
        <form method="POST" action="/youngest.jsp">
            <button type="submit">Youngest Patients</button>
        </form>
        <form method="GET" action="/runAlive.html">
            <button type="submit">Alive Patients</button>
        </form>
        <form method="GET" action="/runDead.html">
            <button type="submit">Dead Patients</button>
        </form>
        <form method="POST" action="/dateAfter.jsp">
            <button type="submit">Date After Filter</button>
        </form>
        <form method="POST" action="/dateBefore.jsp">
            <button type="submit">Date Before Filter</button>
        </form>
    </nav>
</div>
</body>
</html>