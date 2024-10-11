<%@ page import="java.util.List" %>
<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<header>
    <div class="container">
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Before Date Filter<span style="float: right;">Jash Shah</span></h1>
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

<div class="main">
    <form method="POST" action="/runDateBefore.html">
        <input type="text" name="dateSearchString" placeholder="Enter date in yyyy-mm-dd format" style=" width: 275px"/>
        <button type="submit" style="margin-top: 20px; margin-left: 20px; padding: 6px 10px; float: left;">Submit</button>
    </form>
</div>

<div style="display: flex">
    <%
        String dateSearchString = (String) request.getAttribute("dateSearchString");
        int count = (int) request.getAttribute("count");
    %>
    <h4> Date Searched: <%= dateSearchString %></h4>
    <h4>No. of Patients Born Before: <%=count%></h4>
</div>

<%
    List<String> patients = (List<String>) request.getAttribute("patientNames");
    List<String> ids = (List<String>) request.getAttribute("ids");
    List<String> dobs = (List<String>) request.getAttribute("dobs");
%>
<div style=" margin-top: 20px; display: flex; justify-content: center; align-items: center;">
    <table style="text-align: left; position: relative;">
        <tr>
            <th style="position: sticky; top: 0; z-index: 2;">Patients</th>
            <th style="position: sticky; top: 0; z-index: 2;">BIRTHDATE</th>
        </tr>
        <%
            int index = 0;
            for (String patient : patients)
            {
                String id = ids.get(index);
                String href = "patientData.html/?patientId=" + id;
                String dob = dobs.get(index);
        %>
        <tr>
            <td style="border: 1px solid black; text-align: center; padding: 10px;"><a href="<%=href%>" ><%=patient%></a></td>
            <td style="border: 1px solid black; text-align: center; padding: 10px;"><%=dob%></td>
        </tr>
        <%
                index += 1;
            }
        %>
    </table>

</div>
</body>
</html>
