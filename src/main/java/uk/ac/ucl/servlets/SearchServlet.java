package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/runsearch.html")
public class SearchServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Model model = ModelFactory.getModel();

    List<String> columnNames = model.getDataFrame().getColumnNames();
    request.setAttribute("columnNames", columnNames);

    String searchString = request.getParameter("searchstring");
    String searchColumn = request.getParameter("searchcolumn");
    request.setAttribute("search", searchString);
    if(searchColumn.isEmpty()){
      request.setAttribute("column", "All Columns");
    }
    else {
      request.setAttribute("column", searchColumn);
    }
    List<String> searchResult = model.searchFor(searchString,searchColumn);
    if(searchResult == null){
      request.setAttribute("wrongColumn", true);
    }
    else {
      request.setAttribute("wrongColumn", false);
      List<List<String>> searchedRecords = new ArrayList<>();
      for (String id : searchResult) {
        searchedRecords.add(model.getRecordById(id));
      }
      request.setAttribute("searchedRecords", searchedRecords);
    }

      ServletContext context = getServletContext();
      RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
      dispatch.forward(request, response);
  }
}
