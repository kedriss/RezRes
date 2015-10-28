package fil.servlets.admin;

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

import fil.bean.jpa.ReservationEntity;
import fil.bean.jpa.RessourceEntity;
import fil.bean.jpa.TypeRessourceEntity;
import fil.persistence.services.jpa.RessourcePersistenceJPA;
import fil.persistence.services.jpa.TypeRessourcePersistenceJPA;
import fil.servlets.AdminServlet;
import fil.util.Messages;

@WebServlet("/admin/types/*")
public class GestTypeRessources extends AdminServlet {
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
		try
		{
			rd.forward(request, response);
		} 
		catch (IllegalStateException e) {}
	}
	
	private void modifyAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		TypeRessourcePersistenceJPA service = new TypeRessourcePersistenceJPA();
		String nom = request.getParameter("nom");
		String param = request.getParameter("cle");
		try 
		{
			Integer cle = Integer.parseInt(param);
			if(nom != null && nom != "" && cle != null)
			{
				TypeRessourceEntity new_type_res = service.load(cle);
				new_type_res.setLibelle(nom);
				service.save(new_type_res);
				Messages.TypeRessourceModified.setMessage(request);
			}
			else if( (nom == null || nom == "") && cle != null)
			{
				TypeRessourceEntity new_type_res = service.load(cle);
				request.setAttribute("mod_form", true);
				request.setAttribute("old_cle", new_type_res.getCle());
				request.setAttribute("old_name", new_type_res.getLibelle());
			}
			else
			{
				Messages.TypeRessourceModifyDeniedID.setMessage(request);
			}
		}
		catch (NumberFormatException e)
		{
			 Messages.BADTypeRessource.setMessage(request);
			this.showAction(request, response);
		}
		finally
		{
			this.showAction(request, response);
		}
	}
	
	private void createAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nom = request.getParameter("nom");
		if(nom != null && nom != "")
		{
			TypeRessourcePersistenceJPA service = new TypeRessourcePersistenceJPA();
			TypeRessourceEntity new_type_res = new TypeRessourceEntity();
			new_type_res.setLibelle(nom);
			service.insert(new_type_res);
			Messages.TypeRessourceCreated.setMessage(request);
		} 
		else
		{
			Messages.TypeRessourceCreationDeniedName.setMessage(request);
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
				
				RessourcePersistenceJPA res_serv = new RessourcePersistenceJPA();
				Map<String, Object> queryParameters = new HashMap<String, Object>();
				queryParameters.put("id", cle);
				List<RessourceEntity> reservations = res_serv.loadByNamedQuery("RessourceEntity.getByType", queryParameters);
				
				if(reservations == null || (reservations != null && reservations.isEmpty()))
					service.delete(cle);
				else
					Messages.TypeRessourceDeleteDenied.setMessage(request);
			}
			this.showAction(request, response);
		}
		catch (NumberFormatException e)
		{
			 Messages.BADTypeRessource.setMessage(request);
				this.showAction(request, response);
		}
	}

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.handleRequest(request, response);
		
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
			this.modifyAction(request, response);
			break;
		default:
			this.showAction(request, response);
		}
	}
}
