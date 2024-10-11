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


@WebServlet("/runOldest.html")
public class OldestServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();

        List<String> ids = new ArrayList<>();
        List<String> oldestNums = new ArrayList<>();
        int rowCount = model.getDataFrame().getRowCount();


        request.setAttribute("oldestSearch",request.getParameter("oldestSearchString"));
        request.setAttribute("deadNum",model.getDeadPatients().size());
        request.setAttribute("rowCount",Integer.toString(rowCount));

        if((Integer.parseInt(request.getParameter("oldestSearchString")) <= rowCount - model.getDeadPatients().size()))
        {
            List<List<String>> oldestResult = model.getMostOldest(request.getParameter("oldestSearchString"));
            for (int index = 0; index < oldestResult.size(); index++) {
                ids.add(oldestResult.get(index).get(1));
            }
            request.setAttribute("ids", ids);

            List<String> patientNames = model.getSelectivePatientNames(ids);
            request.setAttribute("patientNames",patientNames);

            for (int index = 0; index < oldestResult.size(); index++) {
                oldestNums.add((oldestResult.get(index)).getFirst());
            }
            request.setAttribute("oldestNums", oldestNums);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/oldestResult.jsp");
        dispatch.forward(request, response);
    }
}
