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

@WebServlet("/ageGraph.html")
public class AgeGraphServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        List<String> ageRanges = new ArrayList<>(model.hashForAgeGraphs().keySet());
        List<Integer> count = new ArrayList<>(model.hashForAgeGraphs().values());

        request.setAttribute("ageRanges", ageRanges);
        request.setAttribute("count", count);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/ageGraph.jsp");
        dispatch.forward(request, response);
    }


}
