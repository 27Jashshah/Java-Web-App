<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<header>
    <div class="container">
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Ethnicity Distribution Graph<span style="float: right;">Jash Shah</span></h1>
        <div style="display: flex; flex-direction: column; justify-content: center;">
            <form method="POST" action="/index.jsp" style="margin-left: 20px">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go Back to Homepage</button>
            </form>
            <form method="POST" action="/graphAnalysis.jsp" style="margin-left: 20px;">
                <button type="submit" style=" padding: 6px 10px; float: left;">< Go Back</button>
            </form>
        </div>
    </div>
</header>
<div>
    <form method="POST" action="/ethnicityGraph.html">
        <input type="text" name="raceSearched" placeholder="Enter Race here (case sensitive)" style=" width: 275px; margin-top: 20px"/>
        <button type="submit" style="margin-top: 20px; margin-left: 20px; padding: 6px 10px; float: left;">Submit</button>
    </form>
</div>
<div>
    <h4> Entered race: <%= request.getAttribute("raceSearched") %></h4>
</div>
<%
    if(!(boolean) request.getAttribute("wrongRace"))
    {
%>
<div style="height: 750px; width: 750px; margin: auto;">
    <canvas id="ethnicityGraph"></canvas>

    <script type="text/javascript">
        const ctx = document.getElementById("ethnicityGraph");
        const data = {
            labels: <%=request.getAttribute("ethnicities")%>,
            datasets: [{
                data: <%=request.getAttribute("ethnicityCount")%>,

            }],
        };
        const config = {
            type: 'pie',
            data: data,
            options: {
                plugins: {
                    legend: {
                        display: true,
                        position: 'right',
                    },
                },
            },
        };
        new Chart(ctx, config);
    </script>
</div>
<%} else{ %>
<div style="display: flex; justify-content: center; align-items: center; height: 100vh; float: left">
    <table>
        <tr>
            <td style="border: 1px solid black; text-align: center; padding: 100px;"> There Are No Patients With The Race Searched</td>
        </tr>
    </table>
</div>
<%}%>
</body>
</html>