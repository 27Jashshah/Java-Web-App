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
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Oldest Patients<span style="float: right;">Jash Shah</span></h1>
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
    <form method="POST" action="/runOldest.html">
        <input type="text" name="oldestSearchString" placeholder="Enter number here" style="width: 250px"/>
        <button type="submit" style="margin-top: 20px; margin-left: 20px; padding: 6px 10px; float: left;">Submit</button>
    </form>
</div>

<div>
    <%
        String oldestSearch = (String) request.getAttribute("oldestSearch");
        String rowCount = (String) request.getAttribute("rowCount");
    %>
    <h4> Entered number: <%= oldestSearch %></h4>
    <%
        Integer deadNum = (Integer) request.getAttribute("deadNum");
    %>
</div>
<%
    if(Integer.parseInt(oldestSearch) <= (Integer.parseInt(rowCount)) - deadNum){

        List<String> patients = (List<String>) request.getAttribute("patientNames");
        List<String> ids = (List<String>) request.getAttribute("ids");
        List<String> numbering = (List<String>) request.getAttribute("oldestNums");
%>
<div style=" margin-top: 20px; display: flex; justify-content: center; align-items: center;">
    <table style="text-align: left; position: relative;">
        <tr>

            <th style="position: sticky; top: 0; z-index: 2;">No.</th>
            <th style="position: sticky; top: 0; z-index: 2;">Patients</th>
        </tr>
        <%

            int index = 0;
            for (String patient : patients)
            {
                String id = ids.get(index);
                String href = "patientData.html/?patientId=" + id;
                String num = numbering.get(index);
        %>
        <tr>
            <td style="border: 1px solid black; text-align: center; padding: 10px;"><%=num%></td>
            <td style="border: 1px solid black; text-align: center; padding: 10px;"><a href="<%=href%>" ><%=patient%></a></td>
        </tr>
        <%
                index += 1;
            }
        %>

    </table>
        <%} else if (Integer.parseInt(oldestSearch) > (Integer.parseInt(rowCount))){%>
        <div style="display: flex; justify-content: center; align-items: center; height: 100vh; float: left">
            <table>
                <tr>
                    <td style="border: 1px solid black; text-align: center; padding: 100px;"> Number Entered Is Greater Than Patients Records</td>
                </tr>
            </table>
        </div>
        <%} else{%>
    <div style="display: flex; justify-content: center; align-items: center; height: 100vh; float: left">
        <table>
            <tr>
                <td style="border: 1px solid black; text-align: center; padding: 100px;"> Number Entered Is Greater Than Patients Alive</td>
            </tr>
        </table>
    </div>
    <%}%>
</div>
</body>
</html>
