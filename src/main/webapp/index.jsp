<%@ page import="java.io.File" %>
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
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Homepage<span style="float: right;">Jash Shah</span></h1>
    </div>
</header>
<div>
    <nav>
        <form method="GET" action="/patientList.html">
            <button type="submit">View Patient List</button>
        </form>
        <form method="POST" action="/search.jsp">
            <button type="submit">Search Patient By Keyword</button>
        </form>
        <form method="POST" action="/dateFilters.jsp">
            <button type="submit">Date Filters</button>
        </form>
        <form method="POST" action="/graphAnalysis.jsp">
            <button type="submit">Graph Analysis</button>
        </form>
        <form method="GET" action="/saveJSON.html">
            <button type="submit">Download Patients JSON</button>
        </form>

    </nav>
</div>
</body>
</html>