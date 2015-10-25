package fil.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = -7782175617962874489L;

	protected void checkSession(HttpServletResponse response, HttpSession session) throws IOException {
		if(!isConnected(session))
		{ 
			response.sendRedirect("/RezRes/login");
		}
	}

	protected boolean isConnected(HttpSession session) {
		return session.getAttribute("connected") != null && (boolean) session.getAttribute("connected") == true;
	}
	
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.checkSession(response, request.getSession());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
}
