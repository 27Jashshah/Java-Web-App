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
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Dead Patients<span style="float: right;">Jash Shah</span></h1>
        <div style="display: flex; flex-direction: column; justify-content: center;">
            <form method="POST" action="/index.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go Back to Homepage</button>
            </form>
            <form method="POST" action="/dateFilters.jsp" style="margin-left: 20px;">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go Back</button>
            </form>
        </div>
    </div>
</header>

<div>
    <%
        String deadNumber = (String) request.getAttribute("deadNumber");
    %>
    <h4> Number of Patients Dead: <%= deadNumber %></h4>
</div>

<div style=" margin-top: 20px; display: flex; justify-content: center; align-items: center;">
<table>
    <th>Patients</th>
    <%
        List<String> patients = (List<String>) request.getAttribute("patientNames");
        List<String> ids = (List<String>) request.getAttribute("ids");

        int index = 0;
        for (String patient : patients)
        {
            String id = ids.get(index);
            String href = "patientData.html/?patientId=" + id;
    %>
    <tr><td style="border: 1px solid black; text-align: center; padding: 10px;"><a href="<%=href%>" ><%=patient%></a></td></tr>
    <%
            index += 1;
        }
    %>
</table>
</div>
</body>
</html>
