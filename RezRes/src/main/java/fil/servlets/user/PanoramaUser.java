package fil.servlets.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fil.servlets.UserServlet;

@WebServlet("/user")
public class PanoramaUser extends UserServlet 
{
	private static final long serialVersionUID = 749223966274794536L;

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.handleRequest(request, response);
		
		String target = "/JSP/pages/user/panorama.jsp";
		request.setAttribute("title", "RezRes - Utilisateur");
		request.setAttribute("body", "Panorama");
		request.setAttribute("menu_entry", 0);
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(target);
		try
		{
			rd.forward(request, response);
		} 
		catch (IllegalStateException e) {}
	}
}
