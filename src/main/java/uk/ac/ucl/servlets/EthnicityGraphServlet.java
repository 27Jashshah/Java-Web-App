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


@WebServlet("/ethnicityGraph.html")
public class EthnicityGraphServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Model model = ModelFactory.getModel();

        String race = "'" + request.getParameter("raceSearched") + "'";
        List<String> races = new ArrayList<>(model.hashForGraphs("RACE").keySet());

        request.setAttribute("raceSearched",request.getParameter("raceSearched"));
        if(races.contains(race))
        {
            List<String> ethnicities = new ArrayList<>(model.hashForEthnicityGraphs(race).keySet());
            List<Integer> ethnicityCount = new ArrayList<>(model.hashForEthnicityGraphs(race).values());
            request.setAttribute("ethnicities" , ethnicities);
            request.setAttribute("ethnicityCount" , ethnicityCount);
            request.setAttribute("wrongRace" , false);
        } else{
            request.setAttribute("wrongRace" , true);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/ethnicityGraph.jsp");
        dispatch.forward(request, response);
    }
}
