package fil.servlets.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fil.bean.jpa.TypeResssourceEntity;
import fil.persistence.services.jpa.TypeResssourcePersistenceJPA;

@WebServlet("/admin/types/*")
public class GestTypeRessources extends HttpServlet {
	private static final long serialVersionUID = -4391170416639320134L;
	
	private String getAction(HttpServletRequest request)
	{
		String[] uri = request.getRequestURI().split("/");
		
		if(uri.length >= 5)
			return uri[4];
		else
			return uri[3];
	}
	
	private void showAction(HttpServletRequest request)
	{
		TypeResssourcePersistenceJPA service = new TypeResssourcePersistenceJPA();
		List<TypeResssourceEntity> type_res = service.loadAll();
		request.setAttribute("list_type_res", type_res);
	}
	
	private void createAction(HttpServletRequest request)
	{
		String nom = request.getParameter("nom");
		System.out.println(nom);
		if(nom != null)
		{
			TypeResssourcePersistenceJPA service = new TypeResssourcePersistenceJPA();
			TypeResssourceEntity new_type_res = new TypeResssourceEntity();
			new_type_res.setLibelle(nom);
			service.insert(new_type_res);
		}
	}
	
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String target = "/JSP/pages/admin/gest_type.jsp";
		request.setAttribute("title", "RezRes - Gestion des types de ressources");
		request.setAttribute("body", "Gestion des types de ressources");
		request.setAttribute("menu_entry", 3);
		
		String action = getAction(request);
		switch (action)
		{
		case "create":
			this.createAction(request);
			break;
		case "delete":
			break;
		case "modify":
			break;
		default:
			this.showAction(request);
		}
		
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
