package fil.servlets.user;

import java.io.IOException;
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
import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.jpa.ReservationPersistenceJPA;
import fil.servlets.UserServlet;

@WebServlet("/user")
public class PanoramaUserServlet extends UserServlet 
{
	private static final long serialVersionUID = 749223966274794536L;

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.handleRequest(request, response);
		
		String target = "/JSP/pages/user/panorama_user.jsp";
		request.setAttribute("title", "RezRes - Utilisateur");
		request.setAttribute("body", "Panorama");
		request.setAttribute("menu_entry", 0);
		
		HttpSession session = request.getSession();
		UtilisateurEntity user = (UtilisateurEntity) session.getAttribute("utilisateurConnecte");
		if(user != null)
		{
			ReservationPersistenceJPA res_manager = new ReservationPersistenceJPA();
			
			Map<String, Object> queryParameters = new HashMap<>();
			queryParameters.put("idu", user.getId());
			List<ReservationEntity> res = res_manager.loadByNamedQuery("ReservationEntity.getAllByUser", queryParameters);
			request.setAttribute("reservations", res);
		}
		
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
