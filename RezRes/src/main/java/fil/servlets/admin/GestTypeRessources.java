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

import fil.bean.jpa.TypeRessourceEntity;
import fil.persistence.services.jpa.TypeRessourcePersistenceJPA;

@WebServlet("/admin/types/*")
public class GestTypeRessources extends HttpServlet {
	private static final long serialVersionUID = -4391170416639320134L;
	private static final String target = "/JSP/pages/admin/gest_type.jsp";

	private String getAction(HttpServletRequest request)
	{
		String[] uri = request.getRequestURI().split("/");

		if(uri.length >= 5)
			return uri[4];
		else
			return uri[3];
	}

	private void showAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		TypeRessourcePersistenceJPA service = new TypeRessourcePersistenceJPA();
		List<TypeRessourceEntity> type_res = service.loadAll();
		request.setAttribute("list_type_res", type_res);
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(target);
		rd.forward(request, response);
	}
	
	/*
	private void modifyAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nom = request.getParameter("nom");
		String param = request.getParameter("cle");
		try 
		{
			Integer cle = Integer.parseInt(param);
			if(nom != null && cle != null)
			{
				TypeRessourcePersistenceJPA service = new TypeRessourcePersistenceJPA();
				TypeRessourceEntity new_type_res = service.load(cle);
				new_type_res.setLibelle(nom);
				service.insert(new_type_res);
			}
			else
			{
				request.setAttribute("mod_form", true);
				request.setAttribute("old_name", old_name);
			}
		}
		catch (NumberFormatException e)
		{
			request.setAttribute("warning", "Vous n'avez pas pr�cis� de type � supprimer.");
			this.showAction(request, response);
		}
		this.showAction(request, response);
	}
	*/
	
	private void createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nom = request.getParameter("nom");
		if(nom != null && nom != "")
		{
			TypeRessourcePersistenceJPA service = new TypeRessourcePersistenceJPA();
			TypeRessourceEntity new_type_res = new TypeRessourceEntity();
			new_type_res.setLibelle(nom);
			service.insert(new_type_res);
		} 
		else
		{
			request.setAttribute("warning", "Impossible de cr�er le type demand� : pas de nom.");
		}
		this.showAction(request, response);
	}

	private void deleteAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String param = request.getParameter("cle");
		try 
		{
			Integer cle = Integer.parseInt(param);
			if(cle != null)
			{
				TypeRessourcePersistenceJPA service = new TypeRessourcePersistenceJPA();
				service.delete(cle);
			}
			this.showAction(request, response);
		}
		catch (NumberFormatException e)
		{
			request.setAttribute("warning", "Vous n'avez pas pr�cis� de type � supprimer.");
			this.showAction(request, response);
		}
	}

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		request.setAttribute("title", "RezRes - Gestion des types de ressources");
		request.setAttribute("body", "Gestion des types de ressources");
		request.setAttribute("menu_entry", 3);

		String action = getAction(request);

		switch (action)
		{
		case "create":
			this.createAction(request, response);
			break;
		case "delete":
			this.deleteAction(request, response);
			break;
		case "modify":
			break;
		default:
			this.showAction(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
}
