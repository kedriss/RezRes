package fil.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fil.bean.jpa.ReservationEntity;
import fil.persistence.services.jpa.ReservationPersistenceJPA;

@WebServlet("")
public class Home extends HttpServlet {
	private static final long serialVersionUID = -594667926099562461L;
	
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = "/JSP/pages/common/main.jsp";
		request.setAttribute("title", "RezRes");
		request.setAttribute("body", "Bienvenue sur RezRes !");
		
		List<ReservationEntity> ent = new ReservationPersistenceJPA().loadAll();
		
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
