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


@WebServlet("/runYoungest.html")
public class YoungestServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();

        List<String> ids = new ArrayList<>();
        List<String> youngestNums = new ArrayList<>();
        int rowCount = model.getDataFrame().getRowCount();

        request.setAttribute("youngestSearch",request.getParameter("youngestSearchString"));
        request.setAttribute("deadNum",model.getDeadPatients().size());
        request.setAttribute("rowCount",Integer.toString(rowCount));

        if((Integer.parseInt(request.getParameter("youngestSearchString")) <= rowCount - model.getDeadPatients().size()))
        {
            List<List<String>> youngestResult = model.getMostYoungest(request.getParameter("youngestSearchString"));
            for (int index = 0; index < youngestResult.size(); index++) {
                ids.add(youngestResult.get(index).get(1));
            }
            request.setAttribute("ids", ids);

            List<String> patientNames = model.getSelectivePatientNames(ids);
            request.setAttribute("patientNames",patientNames);

            for (int index = 0; index < youngestResult.size(); index++) {
                youngestNums.add((youngestResult.get(index)).getFirst());
            }
            request.setAttribute("youngestNums", youngestNums);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/youngestResult.jsp");
        dispatch.forward(request, response);
    }
}
