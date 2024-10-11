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
@WebServlet("/runAddRecord.html")
public class AddRecordServlet extends HttpServlet
{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        List<String> columnNames = model.getDataFrame().getColumnNames();

        request.setAttribute("columnNames", columnNames);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/addRecord.jsp");
        dispatch.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        List<String> columnNames = model.getDataFrame().getColumnNames();
        List<String> record = new ArrayList<>();

        for(String column : columnNames){
            record.add(request.getParameter(column + "data"));
        }

        model.addRecord(record);

        response.sendRedirect("/patientList.html");
    }


}