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

import fil.bean.jpa.RessourceEntity;
import fil.bean.jpa.TypeRessourceEntity;
import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.RessourcePersistence;
import fil.persistence.services.TypeRessourcePersistence;
import fil.persistence.services.jpa.RessourcePersistenceJPA;
import fil.persistence.services.jpa.TypeRessourcePersistenceJPA;
import fil.persistence.services.jpa.UtilisateurPersistenceJPA;
import fil.servlets.AdminServlet;
import fil.util.Messages;

@WebServlet("/admin/ressources/*")
public class GestRessources extends AdminServlet {
	
	private static final long serialVersionUID = -5112025367936813560L;
	private static String JSP_RES_PATH = "/JSP/pages/admin/gest_res.jsp";
	
	private void deleteRessource(HttpServletRequest request)
	{
		RessourcePersistence ressourceService = new RessourcePersistenceJPA();
		int id = Integer.parseInt(request.getParameter("id"));
		
		RessourcePersistenceJPA res_serv = new RessourcePersistenceJPA();
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("idr", id);
		List<RessourceEntity> reservations = res_serv.loadByNamedQuery("ReservationEntity.getAllByRessource", queryParameters);
		
		if(reservations == null || (reservations != null && reservations.isEmpty()))
			ressourceService.delete(id);
		else
			Messages.RESSOURCEDELETEDENIED.setMessage(request);
	}
	
	//TODO Tester si attribut non null pour ceux nécessaire
	private void updateRessource(HttpServletRequest request)
	{	
		int id    = Integer.parseInt(request.getParameter("id"));
		
		RessourcePersistence ressourceService = new RessourcePersistenceJPA();
		RessourceEntity updating = ressourceService.load(id);
		
		//Récupération des modifications
		String nom 		= request.getParameter("nom");
		String description 	= request.getParameter("description");
		int responsable 	= Integer.parseInt(request.getParameter("responsable"));
		UtilisateurEntity responsableU = new UtilisateurPersistenceJPA().load(responsable);
		String localite = request.getParameter("localite");
		int typeId 	= Integer.parseInt(request.getParameter("type"));
			
		updating.setNom(nom);
		updating.setDescription(description);
		updating.setLocalite(localite);
		updating.setUtilisateur(responsableU);

		//Récupération du type correspondant à l'id
		TypeRessourcePersistence typeRessourceService = new TypeRessourcePersistenceJPA();
		TypeRessourceEntity type = typeRessourceService.load(typeId);
		
		updating.setTypeRessource(type);
		
		ressourceService.save(updating);
			
		Messages.RESSOURCEMODIFIED.setMessage(request);
		getAllRessources(request);
		//request.setAttribute("", "active");
	}
	
	//TODO Tester si attribut non null pour ceux nécessaire
	private void createRessource(HttpServletRequest request)
	{
		
		RessourcePersistence ressourceService = new RessourcePersistenceJPA();
		RessourceEntity creating = new RessourceEntity();
		
		//Récupération des modifications
		String nom 		= request.getParameter("nom");
		String description 	= request.getParameter("description");
		int responsable 	= Integer.parseInt(request.getParameter("responsable"));
		UtilisateurEntity responsableU = new UtilisateurPersistenceJPA().load(responsable);
		String localite = request.getParameter("localite");
		int typeId 	= Integer.parseInt(request.getParameter("type"));
			
		creating.setNom(nom);
		creating.setDescription(description);
		creating.setLocalite(localite);
		creating.setUtilisateur(responsableU);

		//Récupération du type correspondant à l'id
		TypeRessourcePersistence typeRessourceService = new TypeRessourcePersistenceJPA();
		TypeRessourceEntity type = typeRessourceService.load(typeId);
		
		creating.setTypeRessource(type);
		
		ressourceService.save(creating);
			
		Messages.RESSOURCECREATED.setMessage(request);
		getAllRessources(request);	
	}
	
	private void getAllRessources(HttpServletRequest request)
	{
		List<RessourceEntity> ressources = new RessourcePersistenceJPA().loadAll();
		
		request.setAttribute("ressources", ressources);		
	}
	
	private void getRessource(HttpServletRequest request)
	{
		int id    = Integer.parseInt(request.getParameter("id"));
		RessourcePersistence ressourceService = new RessourcePersistenceJPA();
		
		RessourceEntity updating = ressourceService.load(id);
		
		request.setAttribute("ressource", updating);
	}
			
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		super.handleRequest(request, response);
		
		request.setAttribute("title", "RezRes - Gestion des ressources");
		request.setAttribute("body", "Gestion des ressources");
		request.setAttribute("menu_entry", 4);
		
		String pathInfo= request.getPathInfo();
		
		switch (pathInfo+"")
		{
		case "/delete":
			deleteRessource(request);
			getAllRessources(request);
			break;
		case "/modify":
			updateRessource(request);
			getAllRessources(request);
			break;
		case "/modify/form":
			getRessource(request);
			request.setAttribute("modification", true);

			TypeRessourcePersistenceJPA service = new TypeRessourcePersistenceJPA();
			List<TypeRessourceEntity> type_res = service.loadAll();
			request.setAttribute("typeRessources", type_res);
			List<UtilisateurEntity> users = new UtilisateurPersistenceJPA().loadAll();
			request.setAttribute("Utilisateurs", users);
			break;				
		case "/create":
			createRessource(request);
			getAllRessources(request);
			break;
		case "/create/form":
			request.setAttribute("creation", true);

			TypeRessourcePersistenceJPA service2 = new TypeRessourcePersistenceJPA();
			List<TypeRessourceEntity> type_res2 = service2.loadAll();
			request.setAttribute("typeRessources", type_res2);
			List<UtilisateurEntity> entities = new UtilisateurPersistenceJPA().loadAll();
			request.setAttribute("Utilisateurs", entities);

			break;			
		default:
			getAllRessources(request);
			break;
		}
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(JSP_RES_PATH);
		try
		{
			rd.forward(request, response);
		} 
		catch (IllegalStateException e) {}
	}
}
