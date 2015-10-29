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

import org.apache.log4j.Logger;

import fil.bean.jpa.ReservationEntity;
import fil.bean.jpa.RessourceEntity;
import fil.bean.jpa.TypeRessourceEntity;
import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.jpa.ReservationPersistenceJPA;
import fil.persistence.services.jpa.RessourcePersistenceJPA;
import fil.persistence.services.jpa.TypeRessourcePersistenceJPA;
import fil.servlets.UserServlet;
import fil.util.Messages;

@WebServlet("/user/reservation/*")
public class ReservationServlet extends UserServlet {
	private static final long serialVersionUID = -7239735635468259205L;
	private static final SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static Logger logger = Logger.getLogger(ReservationServlet.class);

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.handleRequest(request, response);
		
		logger.info("Arrivée dans la servlet reservation");
		
		String pathInfo = request.getPathInfo();
		String target = "/JSP/pages/user/panorama.jsp";
		switch (pathInfo + "") {

		case "/delete":
			deleteAction(request, response);
			response.sendRedirect("/RezRes/user");
			break;
		case "/create":
			createAction(request, response);
			response.sendRedirect("/RezRes/user");
			break;
		default:
			formAction(request, response);
			target = "/JSP/pages/user/reservation.jsp";
			break;
		}

		request.setAttribute("title", "RezRes - Reservation");
		request.setAttribute("body", "Réserver");
		request.setAttribute("menu_entry", 1);

		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(target);
		try {

			rd.forward(request, response);
		} catch (IllegalStateException e) {
		}
	}

	private void createAction(HttpServletRequest request, HttpServletResponse response) {

		int idRessource = Integer.valueOf(request.getParameter("id"));
		RessourcePersistenceJPA ressourceMananger = new RessourcePersistenceJPA();
		
		HttpSession session = request.getSession();
		
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endDate = request.getParameter("endDate");
		String endTime = request.getParameter("endTime");

		if (isNullDate(startDate, startTime, endDate, endTime)) {			
		Messages.InputsEmpty.setMessage(request);	 
		}
		else{
			try {
				
				Date startDateTime = dateParser.parse(startDate + " " + startTime);
				Date endDateTime = dateParser.parse(endDate + " " + endTime);
				if (CheckDateCoherence(startDateTime, endDateTime)){				
					ReservationPersistenceJPA reservationManager = new ReservationPersistenceJPA();
					ReservationEntity reservation = new ReservationEntity();
					reservation.setDateDebut(startDateTime);
					reservation.setDateFin(endDateTime);
					reservation.setRessource(ressourceMananger.load(idRessource));
					UtilisateurEntity user = (UtilisateurEntity) session.getAttribute("utilisateurConnecte");
					reservation.setUtilisateur(user);
	
					reservationManager.save(reservation);
	
					request.setAttribute("created", true);
				}else{
					Messages.ReservationBadDate.setMessage(request);
				}
				
			} catch (ParseException e) {
				Messages.BadDateFormat.setMessage(request);
			}
		}

	}

	private boolean CheckDateCoherence(Date startDateTime, Date endDateTime) {
		return startDateTime.before(endDateTime);
	}

	private boolean isNullDate(String startDate, String startTime,
			String endDate, String endTime) {
		return startDate == null || startTime == null || endDate == null || endTime == null;
	}

	private void deleteAction(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");

		if (id != null) {
			ReservationPersistenceJPA reservationManager = new ReservationPersistenceJPA();
			reservationManager.delete(Integer.valueOf(id));
			Messages.RESSOURCEDELETED.setMessage(request);
		}
	}

	private void formAction(HttpServletRequest request, HttpServletResponse response) {
		String typeressource = request.getParameter("type");
		
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endDate = request.getParameter("endDate");
		String endTime = request.getParameter("endTime");

		// On arrive sur le formulaire vide
		if (isNullDate(typeressource, startDate, startTime, endDate) || endTime == null ) {
			setUpForm(request, response);
		} else {
			try {
				Date startDateTime = dateParser.parse(startDate + " " + startTime);
				Date endDateTime = dateParser.parse(endDate + " " + endTime);
				request.setAttribute("startDate", startDate);
				request.setAttribute("startTime", startTime);
				request.setAttribute("endDate", endDate);
				request.setAttribute("endTime", endTime);
				
				if (CheckDateCoherence(startDateTime, endDateTime)){
					Map<String, Object> criters = new HashMap<String, Object>();
					
					criters.put("type", Integer.valueOf(typeressource));
					criters.put("date_debut", startDateTime);
					criters.put("date_fin", endDateTime);
					RessourcePersistenceJPA ressourceManager = new RessourcePersistenceJPA();
					List<RessourceEntity> ressourcesLibres = ressourceManager
							.loadByNamedQuery("RessourceEntity.getFreeRessource", criters);
	
					
					
					request.setAttribute("ressources", ressourcesLibres);
				}
				else {
					Messages.ReservationBadDate.setMessage(request);
					setUpForm(request, response);
				}
			} catch (ParseException e) {
				Messages.BadDateFormat.setMessage(request);
				setUpForm(request, response);
			}

		}

	}
	
	private void setUpForm(HttpServletRequest request, HttpServletResponse response){
		TypeRessourcePersistenceJPA typeRessourceManager = new TypeRessourcePersistenceJPA();
		List<TypeRessourceEntity> typeressources = typeRessourceManager.loadAll();
		request.setAttribute("formCreate", true);
		request.setAttribute("typeRessources", typeressources);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}
}
