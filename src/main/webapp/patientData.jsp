<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<header>
    <div class="container">
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Patient Data<span style="float: right;">Jash Shah</span></h1>
        <div style="display: flex; flex-direction: row; justify-content: center;">
            <form method="GET" action="/index.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Homepage</button>
            </form>
            <form method="GET" action="/patientList.html" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Patient List</button>
            </form>
            <form method="GET" action="/oldest.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Oldest Patients</button>
            </form>
            <form method="GET" action="/youngest.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Youngest Patients</button>
            </form>
            <form method="GET" action="/runAlive.html" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Alive Patients</button>
            </form>
            <form method="GET" action="/runDead.html" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Dead Patient</button>
            </form>
            <form method="GET" action="/dateAfter.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Date After Filter</button>
            </form>
            <form method="GET" action="/dateBefore.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go to Date Before Filter</button>
            </form>
        </div>
    </div>
</header>
<%
    String href = "/runDeleteRecord.html/?patientId=" + ((String) request.getAttribute("id"));
%>
<div style="display: flex; justify-content: right; margin-right: 20px;">
    <button style="padding: 6px 10px; margin-top: 20px;" onclick="window.location.href='<%=href%>';">Delete Record</button>
</div>
<div id="managerTable">
    <%
        List<String> columnNames = (List<String>) request.getAttribute("columnNames");
    %>
    <table style="text-align: left; position: relative; margin-top: 250px;">
        <tr>
            <%
                for(String columnName : columnNames)
                {
            %>
            <th><%=columnName%></th>
            <%}%>
        </tr>
        <tr>
            <%
                for(String columnName : columnNames)
                {
                    String value = (String) request.getAttribute(columnName);
            %>
            <td style="border: 1px solid black; text-align: center; padding: 10px;"><%= value %></td>
            <%}%>
        </tr>
    </table>
</div>
</body>
</html>