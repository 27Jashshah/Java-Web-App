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

@WebServlet("/genderGraph.html")
public class GenderGraphServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        List<String> genders = new ArrayList<>(model.hashForGraphs("GENDER").keySet());
        List<Integer> count = new ArrayList<>(model.hashForGraphs("GENDER").values());

        request.setAttribute("genders", genders);
        request.setAttribute("count", count);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/genderGraph.jsp");
        dispatch.forward(request, response);
    }


}
