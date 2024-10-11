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
    <h1 style="text-align: center;"><span style="float: left;">Patient Data App</span>Search Patient By Keyword<span style="float: right;">Jash Shah</span></h1>
    <div style="display: flex; flex-direction: column; justify-content: center;">
    <form method="POST" action="/index.jsp" style="margin-left: 20px">
      <button type="submit" style="margin-bottom: 10px; padding: 6px 10px; float: left;">< Go Back to Homepage</button>
    </form>
    </div>
  </div>
</header>
<div style="display: flex">
  <form method="POST" action="/runsearch.html">
    <input type="text" name="searchstring" placeholder="Enter search keyword here" style=" width: 250px; margin-top: 20px"/>
    <input type="text" name="searchcolumn" placeholder="Enter column here" style=" width: 250px; margin-top: 20px"/>
    <button type="submit" style="margin-top: 20px; margin-left: 20px; padding: 6px 10px; float: left;">Search</button>
  </form>
  <h4>Search keyword is: <%= request.getAttribute("search") %>, in column: <%= request.getAttribute("column") %> </h4>
</div>
    <%
      if((boolean) request.getAttribute("wrongColumn"))
      {%>
<div style="display: flex; justify-content: center; align-items: center; height: 100vh; float: right; margin-right: 400px">
  <table>
    <tr>
      <td style="border: 1px solid black; text-align: center; padding: 100px;">Invalid Column Entered</td>
    </tr>
  </table>
</div>
<%}else{%>
      <%
      List<String> columnNames = (List<String>) request.getAttribute("columnNames");
      List<List<String>> records = (List<List<String>>) request.getAttribute("searchedRecords");
      if(!records.isEmpty())
      {
      %>
  <div id="managerTable">
    <table style="text-align: left; position: relative;">
      <tr>
        <%
          for(int colIndex = 1; colIndex < columnNames.size(); colIndex ++)
          {
            String columnName = columnNames.get(colIndex);
        %>
        <th style="position: sticky; top: 0; z-index: 2;"><%=columnName%></th>
        <%}%>
      </tr>
      <%
        for (List<String> record : records)
        {
      %>
    <tr>
      <%
        for(int colIndex = 1; colIndex < columnNames.size(); colIndex ++)
        {
          String value = record.get(colIndex);
      %>
      <td style="border: 1px solid black; text-align: center; padding: 10px;"><%=value%></td>
      <% }%>
    </tr>
      <%}%>
    </table>
  </div>
      <%} else
      { %>
    <div style="display: flex; justify-content: center; align-items: center; height: 100vh; float: right; margin-right: 400px">
      <table>
        <tr>
          <td style="border: 1px solid black; text-align: center; padding: 100px;"> No Patients Found</td>
        </tr>
      </table>
    </div>
      <%}}%>

</body>
</html>