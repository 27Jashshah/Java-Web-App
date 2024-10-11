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
import java.util.*;

@WebServlet("/raceGraph.html")
public class RaceGraphServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Model model = ModelFactory.getModel();
        List<String> races = new ArrayList<>(model.hashForGraphs("RACE").keySet());
        List<Integer> raceCount = new ArrayList<>(model.hashForGraphs("RACE").values());

        request.setAttribute("races", races);
        request.setAttribute("raceCount", raceCount);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/raceGraph.jsp");
        dispatch.forward(request, response);
    }


}
