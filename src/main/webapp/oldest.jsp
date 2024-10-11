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

<div class="main">
    <form method="POST" action="/runOldest.html">
        <input type="text" name="oldestSearchString" placeholder="Enter number here" style=" width: 250px"/>
        <button type="submit" style="margin-top: 20px; margin-left: 20px; padding: 6px 10px; float: left;">Submit</button>
    </form>
</div>
</body>
</html>
