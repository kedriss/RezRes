package fil.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminServlet extends UserServlet {
	private static final long serialVersionUID = 135725359186707701L;
	
	protected void checkSession(HttpServletResponse response, HttpSession session) throws IOException 
	{
		if(isConnected(session))
		{
			if(!isAdmin(session))
			{
				response.sendRedirect("/RezRes/login");
			}
		}
		else
		{
			response.sendRedirect("/RezRes/login");
		}
	}
	
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.checkSession(response, request.getSession());
	}

	protected boolean isAdmin(HttpSession session) 
	{
		return session.getAttribute("admin") != null && (boolean) session.getAttribute("admin") == true;
	}
}
