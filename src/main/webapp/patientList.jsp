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
    <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Patient List<span style="float: right;">Jash Shah</span></h1>
    <form method="POST" action="/index.jsp" style="margin-left: 20px">
      <button type="submit" style=" padding: 6px 10px; float: left; margin-bottom: 20px">< Go Back to Homepage</button>
    </form>
  </div>
</header>
<div style="margin-top: 20px">
  <div style="display: flex; justify-content: right; margin-right: 20px;">
    <form method="GET" action="/runAddRecord.html">
      <button type="submit" style="padding: 6px 10px">Add Record</button>
    </form>
  </div>
  <div style="display: flex; justify-content: center; align-items: center;">
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
</div>
</body>
</html>
