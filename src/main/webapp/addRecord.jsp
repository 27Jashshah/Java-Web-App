<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
    <style>
        label {
            float: left;
            display: block;
            margin-top: 20px;
        }
        .group {
            display: flex;
            margin-top: 20px;
            margin-left: 20px;
        }
    </style>
</head>
<body>
<header>
    <div class="container">
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Patient Data<span style="float: right;">Jash Shah</span></h1>
        <div style="display: flex; flex-direction: column; justify-content: center;">
            <form method="POST" action="/index.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go Back to Homepage</button>
            </form>
            <form method="GET" action="/patientList.html" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go Back</button>
            </form>
        </div>
    </div>
</header>
<div>
    <form method="POST" action="/runAddRecord.html">
        <%
            for(String column: (List<String>) request.getAttribute("columnNames")){

        %>
        <div class="group">
            <label><%=column%> : </label>
            <%
                String name = column + "data";
            %>
            <input type="text" name="<%=name%>" placeholder="Enter data here" style=" width: 250px;"/>
        </div>
        <%}%>
        <button type="submit" style="margin-top: 20px; margin-left: 20px; padding: 6px 10px; float: left;">Add Record</button>
    </form>
</div>
</body>
</html>