package fil.servlets.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fil.bean.jpa.RessourceEntity;
import fil.persistence.services.jpa.ReservationPersistenceJPA;
import fil.persistence.services.jpa.RessourcePersistenceJPA;
import fil.servlets.UserServlet;

@WebServlet("/user/reservation/*")
public class Reservation extends UserServlet {
	private static final long serialVersionUID = -7239735635468259205L;

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.handleRequest(request, response);
		
		String pathInfo= request.getPathInfo();
		
		switch (pathInfo){
	
		case "/delete":
			deleteAction(request, response);
			break;
		default: 
			formAction(request, response);
			break;
		}
		
	
		String target = "/JSP/pages/user/reservation.jsp";
		request.setAttribute("title", "RezRes - Reservation");
		request.setAttribute("body", "Réserver");
		request.setAttribute("menu_entry", 1);
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(target);
		try
		{
			rd.forward(request, response);
		} 
		catch (IllegalStateException e) {}
	}
	
	

	private void checkOverlap(RessourceEntity ressource, String date_debut, String date_fin) {
		// TODO Auto-generated method stub
		
	}

	private void deleteAction(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		
		if(id!=null){
			ReservationPersistenceJPA reservationManager = new ReservationPersistenceJPA();
			reservationManager.delete(Integer.valueOf(id));
			request.setAttribute("suppresion", true);
			
		}
		
	}

	private void formAction(HttpServletRequest request,HttpServletResponse response) {
		RessourcePersistenceJPA 	ressourceManager 	= new RessourcePersistenceJPA();
		List<RessourceEntity> 		ressources 			= ressourceManager.loadAll();
		
		request.setAttribute("formCreate", true);
		request.setAttribute("ressources", ressources);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
}
