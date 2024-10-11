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
        <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Race Distribution Graph<span style="float: right;">Jash Shah</span></h1>
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
<div style="height: 750px; width: 750px; margin: auto;">
    <canvas id="raceGraph"></canvas>

    <script type="text/javascript">
        const ctx = document.getElementById("raceGraph");
        const data = {
            labels: <%=request.getAttribute("races")%>,
            datasets: [{
                data: <%=request.getAttribute("raceCount")%>,

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
</body>
</html>