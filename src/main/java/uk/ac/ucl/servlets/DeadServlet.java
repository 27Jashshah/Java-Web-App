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

// The servlet invoked to perform a search.
// The url http://localhost:8080/runsearch.html is mapped to calling doPost on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/runDead.html")
public class DeadServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();

        List<String> columnNames = model.getDataFrame().getColumnNames();
        request.setAttribute("columnNames", columnNames);

        List<String> ids = model.getDeadPatients();
        request.setAttribute("ids",ids);

        List<String> patientNames = model.getSelectivePatientNames(ids);
        request.setAttribute("patientNames",patientNames);

        request.setAttribute("deadNumber", Integer.toString(ids.size()));

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/dead.jsp");
        dispatch.forward(request, response);
    }
}
