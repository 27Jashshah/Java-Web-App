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
import java.util.HashMap;


@WebServlet("/patientList.html")
public class ViewPatientListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    Model model = ModelFactory.getModel();
    HashMap<String, String> patientIdAndNames = model.getAllPatientNames();

    List<String> patientNames = new ArrayList<>();
    for(String patientName : patientIdAndNames.values())
    {
      patientNames.add(patientName);
    }
    request.setAttribute("patientNames", patientNames);

    List<String> ids = new ArrayList<>();
    for(String id : patientIdAndNames.keySet())
    {
      ids.add(id);
    }
    request.setAttribute("ids", ids);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/patientList.jsp");
    dispatch.forward(request, response);
  }
}

