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
@WebServlet("/runDateBefore.html")
public class DateBeforeServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();

        request.setAttribute("dateSearchString", request.getParameter("dateSearchString"));

        if(!(request.getParameter("dateSearchString").matches("\\d{4}-\\d{2}-\\d{2}"))) {
            System.out.println("Wrong Date Format entered");
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/dateBefore.jsp");
            dispatch.forward(request, response);
        } else{
            List<String> ids = model.dateFilter(request.getParameter("dateSearchString"), "Before");
            List<String> patientNames = model.getSelectivePatientNames(ids);
            request.setAttribute("ids",ids);
            request.setAttribute("patientNames",patientNames);
            request.setAttribute("count",ids.size());

            List<String> dobs = new ArrayList<>();
            for(int index = 0; index < ids.size(); index++)
            {
                if(ids.contains(model.getDataFrame().getValue("ID",index)))
                {
                    dobs.add(model.getDataFrame().getValue("BIRTHDATE",index));
                }
            }
            request.setAttribute("dobs",dobs);
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/dateBeforeResult.jsp");
            dispatch.forward(request, response);
        }
    }
}