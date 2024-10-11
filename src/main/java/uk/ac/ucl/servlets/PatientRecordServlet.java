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
import java.util.List;

@WebServlet("/patientData.html/")
public class PatientRecordServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Model model = ModelFactory.getModel();
        List<String> columnNames = model.getDataFrame().getColumnNames();

        String id = request.getParameter("patientId");
        request.setAttribute("id", id);
        List<String> record = model.getRecordById(id);

        int index = 0;
        for(String value : record)
        {
            request.setAttribute(columnNames.get(index), value);
            index += 1;
        }

        request.setAttribute("columnNames", columnNames);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/patientData.jsp");
        dispatch.forward(request, response);
    }

}
