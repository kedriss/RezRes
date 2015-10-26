package fil.servlets.admin;

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

import fil.bean.jpa.ReservationEntity;
import fil.persistence.services.ReservationPersistence;
import fil.persistence.services.jpa.ReservationPersistenceJPA;
import fil.servlets.AdminServlet;

@WebServlet("/admin/*")
public class PanoramaAdmin extends AdminServlet {
	private static final long serialVersionUID = -5112025367936813560L;
	private static String JSP_RESER_PATH = "/JSP/pages/admin/panorama.jsp";

	private static SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private void getAllRessources(HttpServletRequest request) {
		List<ReservationEntity> reservations = new ReservationPersistenceJPA().loadAll();

		request.setAttribute("reservations", reservations);
	}

	// TODO Exceptions?
	private void getRessourcesFromDates(HttpServletRequest request) {

		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endDate = request.getParameter("endDate");
		String endTime = request.getParameter("endTime");

		if (startDate != null && startTime != null && endDate != null && endTime != null) {
			try {
				Date startDateTime = dateParser.parse(startDate + " " + startTime);
				Date endDateTime = dateParser.parse(endDate + " " + endTime);

				ReservationPersistence reservationService = new ReservationPersistenceJPA();

				Map<String, Object> queryParameters = new HashMap<>();
				queryParameters.put("start", startDateTime);
				queryParameters.put("end", endDateTime);

				List<ReservationEntity> reservations = reservationService
						.loadByNamedQuery("ReservationEntity.filterDates", queryParameters);

				request.setAttribute("reservations", reservations);
			} catch (ParseException e) {
				request.setAttribute("warning", "La date doit être au format 'yyyy-MM-dd' et l'heure au format 'HH:mm'");
				getAllRessources(request);
			}

		}
	}

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.handleRequest(request, response);

		request.setAttribute("title", "RezRes - Gestion des ressources");
		request.setAttribute("body", "Gestion des ressources");
		request.setAttribute("menu_entry", 2);

		String pathInfo = request.getPathInfo();

		if (pathInfo != null) {
			getRessourcesFromDates(request);
		} else {
			getAllRessources(request);
		}
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(JSP_RESER_PATH);
		try {
			rd.forward(request, response);
		} catch (IllegalStateException e) {
		}
	}
}
