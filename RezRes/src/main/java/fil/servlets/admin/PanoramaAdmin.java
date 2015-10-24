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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fil.bean.jpa.ReservationEntity;
import fil.persistence.services.ReservationPersistence;
import fil.persistence.services.jpa.ReservationPersistenceJPA;

@WebServlet("/admin/*")
public class PanoramaAdmin extends HttpServlet {
	private static final long serialVersionUID = -5112025367936813560L;
	private static String JSP_RESER_PATH = "/JSP/pages/admin/panorama.jsp";
	
	private static SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
	
	private void getAllRessources(HttpServletRequest request){
		List<ReservationEntity> reservations = new ReservationPersistenceJPA().loadAll();
		
		request.setAttribute("reservations", reservations);		
	}
	
	//TODO Exceptions?
	private void getRessourcesFromDates(HttpServletRequest request) throws ParseException{
		
		String tmp = request.getParameter("start");
		Date startDate = dateParser.parse(tmp);
		Date endDate = dateParser.parse(request.getParameter("end"));
		
		ReservationPersistence reservationService = new ReservationPersistenceJPA();
		
		Map<String, Object> queryParameters = new HashMap<>();
		queryParameters.put("start", startDate);
		queryParameters.put("end", endDate);
		
		List<ReservationEntity> reservations = reservationService.loadByNamedQuery("ReservationEntity.filterDates", queryParameters);
		
		request.setAttribute("reservations", reservations);
	}
			
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		//HttpSession session = request.getSession();
		
		request.setAttribute("title", "RezRes - Gestion des ressources");
		request.setAttribute("body", "Gestion des ressources");
		request.setAttribute("menu_entry", 4);
		
		String pathInfo = request.getPathInfo();
		
		if(pathInfo != null){
			getRessourcesFromDates(request);
		}
		else{
			getAllRessources(request);
		}
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(JSP_RESER_PATH);
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			handleRequest(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			handleRequest(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
