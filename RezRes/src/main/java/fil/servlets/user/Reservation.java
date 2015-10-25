package fil.servlets.user;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fil.bean.jpa.ReservationEntity;
import fil.bean.jpa.RessourceEntity;
import fil.bean.jpa.TypeRessourceEntity;
import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.jpa.ReservationPersistenceJPA;
import fil.persistence.services.jpa.RessourcePersistenceJPA;
import fil.persistence.services.jpa.TypeRessourcePersistenceJPA;
import fil.persistence.services.jpa.UtilisateurPersistenceJPA;
import fil.servlets.UserServlet;

@WebServlet("/user/reservation/*")
public class Reservation extends UserServlet {
	private static final long serialVersionUID = -7239735635468259205L;
	private static final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.handleRequest(request, response);
		
		String pathInfo= request.getPathInfo();
		
		switch (pathInfo+""){
	
		case "/delete":
			deleteAction(request, response);
			break; 
		case "/create":
			createAction(request, response);
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
	
	

	private void createAction(HttpServletRequest request,HttpServletResponse response) {
		
		int idRessource = Integer.valueOf(request.getParameter("id"));
		RessourcePersistenceJPA ressourceMananger = new RessourcePersistenceJPA();
		UtilisateurPersistenceJPA utilisateurManager = new UtilisateurPersistenceJPA();
		HttpSession session = request.getSession();
		
		try {
			Date debut = dateParser.parse(request.getParameter("start"));
			Date fin   = dateParser.parse(request.getParameter("end"));
			ReservationPersistenceJPA reservationManager = new ReservationPersistenceJPA();
			ReservationEntity reservation = new ReservationEntity();
			reservation.setDateDebut(debut);
			reservation.setDateFin(fin);
			reservation.setRessource(ressourceMananger.load(idRessource));
			UtilisateurEntity user = (UtilisateurEntity)session.getAttribute("utilisateurConnecte");
			reservation.setUtilisateur(user);
			
			reservationManager.save(reservation);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	private void checkOverlap(RessourceEntity ressource, String date_debut, String date_fin) {
		
		
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
		String typeressource = request.getParameter("type");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		
		// On arrive sur le formulaire vide
		if(typeressource == null){
			TypeRessourcePersistenceJPA 	typeRessourceManager 	= new TypeRessourcePersistenceJPA();
			List<TypeRessourceEntity> 		typeressources 			= typeRessourceManager.loadAll();
			request.setAttribute("formCreate", true);
			request.setAttribute("typeRessources", typeressources);
		
		} 
		else {
			
			
			try {
				Date debut = dateParser.parse(request.getParameter("start"));
				Date fin   = dateParser.parse(request.getParameter("end"));
				Map<String,Object>  criters = new HashMap<String, Object>();
				
				criters.put("type", Integer.valueOf(typeressource));
				criters.put("date_debut", debut);
				criters.put("date_fin", fin);
				RessourcePersistenceJPA	ressourceManager 	= new RessourcePersistenceJPA();
				List<RessourceEntity> ressourcesLibres = ressourceManager.loadByNamedQuery("RessourceEntity.getFreeRessource", criters);
			
				request.setAttribute("start", start);
				request.setAttribute("end", end);
				request.setAttribute("ressources", ressourcesLibres);
			} catch (ParseException e) {
				e.printStackTrace();
				request.setAttribute("problemeDate", true);
			}

			
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
}
