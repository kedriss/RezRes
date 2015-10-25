package fil.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.jpa.UtilisateurPersistenceJPA;

@WebServlet("/login/*")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -5270557174439766566L;

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String pathInfo		= request.getPathInfo();

		if("/out".equals(pathInfo))
		{ 
			session.removeAttribute("admin");
			session.removeAttribute("connected");
			session.removeAttribute("utilisateurConnecte");
			redirectOnLoginPage(request, response);
		}
		else
		{
			if(isConnected(session))
			{
				checkSession(response, session);
			}
			else 
			{
				checkLoginPwd(request, session, response);
			}
		}
	}

	private void checkSession(HttpServletResponse response, HttpSession session) throws IOException 
	{
		if(isConnected(session))
		{ 
			if(isAdmin(session))
				response.sendRedirect("/RezRes/admin");
			else
				response.sendRedirect("/RezRes/user");
		}
	}

	private boolean isAdmin(HttpSession session) 
	{
		return session.getAttribute("admin") != null && (boolean) session.getAttribute("admin") == true;
	}

	private boolean isConnected(HttpSession session) 
	{
		return session.getAttribute("connected") != null && (boolean) session.getAttribute("connected") == true;
	}

	private void checkLoginPwd(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws ServletException, IOException 
	{
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");

		if( login != null)
		{ // recherche d'une concordance avec login et mot de passe
			UtilisateurPersistenceJPA utilisateurManager = new UtilisateurPersistenceJPA();
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("login", login);
			criteria.put("pwd", pwd);
			List<UtilisateurEntity> Uentities = utilisateurManager.search(criteria);
			if(Uentities.size() >0)
			{
				UtilisateurEntity utilisateur = Uentities.get(0);
				session.setAttribute("admin", utilisateur.getType()==1);
				session.setAttribute("connected", true);
				session.setAttribute("utilisateurConnecte", utilisateur);
				checkSession(response, session);
			}
			else
			{ 	
				request.setAttribute("connectionRefused", true);
				redirectOnLoginPage(request, response);
			}
		}
		else
		{ 
			redirectOnLoginPage(request, response);
		}
	}

	private void redirectOnLoginPage(HttpServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
		String target = "/JSP/pages/common/login.jsp";
		request.setAttribute("title", "RezRes - Login");
		request.setAttribute("body", "LOGIN");

		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
}
