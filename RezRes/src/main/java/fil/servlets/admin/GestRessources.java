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

import fil.bean.jpa.RessourceEntity;
import fil.bean.jpa.TypeRessourceEntity;
import fil.persistence.services.RessourcePersistence;
import fil.persistence.services.TypeRessourcePersistence;
import fil.persistence.services.jpa.RessourcePersistenceJPA;
import fil.persistence.services.jpa.TypeRessourcePersistenceJPA;

@WebServlet("/admin/ressources")
public class GestRessources extends HttpServlet {
	
	private static final long serialVersionUID = -5112025367936813560L;
	private static String JSP_RES_PATH = "/JSP/pages/admin/gest_res.jsp";
	
	private void deleteRessource(HttpServletRequest request){
		RessourcePersistence ressourceService = new RessourcePersistenceJPA();
		int id = Integer.parseInt(request.getParameter("id"));
		
		ressourceService.delete(id);
	}
	
	//TODO Tester si attribut non null pour ceux nécessaire
	private void updateRessource(HttpServletRequest request){	
		int id    = Integer.parseInt(request.getParameter("id"));
		
		RessourcePersistence ressourceService = new RessourcePersistenceJPA();
		RessourceEntity updating = ressourceService.load(id);
		
		//Récupération des modifications
		String nom 		= request.getParameter("nom");
		String description 	= request.getParameter("description");
		int responsable 	= Integer.parseInt(request.getParameter("mail"));
		String localite = request.getParameter("localite");
		int typeId 	= Integer.parseInt(request.getParameter("type"));
			
		updating.setNom(nom);
		updating.setDescription(description);
		updating.setLocalite(localite);
		updating.setResponsable(responsable);

		//Récupération du type correspondant à l'id
		TypeRessourcePersistence typeRessourceService = new TypeRessourcePersistenceJPA();
		TypeRessourceEntity type = typeRessourceService.load(typeId);
		
		updating.setTypeRessource(type);
		
		ressourceService.save(updating);
			
		request.setAttribute("modified", true);
		getAllRessources(request);
		//request.setAttribute("", "active");
	}
	
	//TODO Tester si attribut non null pour ceux nécessaire
	private void createRessource(HttpServletRequest request){
		
		RessourcePersistence ressourceService = new RessourcePersistenceJPA();
		RessourceEntity creating = new RessourceEntity();
		
		//Récupération des modifications
		String nom 		= request.getParameter("nom");
		String description 	= request.getParameter("description");
		int responsable 	= Integer.parseInt(request.getParameter("mail"));
		String localite = request.getParameter("localite");
		int typeId 	= Integer.parseInt(request.getParameter("type"));
			
		creating.setNom(nom);
		creating.setDescription(description);
		creating.setLocalite(localite);
		creating.setResponsable(responsable);

		//Récupération du type correspondant à l'id
		TypeRessourcePersistence typeRessourceService = new TypeRessourcePersistenceJPA();
		TypeRessourceEntity type = typeRessourceService.load(typeId);
		
		creating.setTypeRessource(type);
		
		ressourceService.save(creating);
			
		request.setAttribute("created", true);
		getAllRessources(request);
		//request.setAttribute("", "active");		
		
		
	}
	
	private void getAllRessources(HttpServletRequest request){
		List<RessourceEntity> ressources = new RessourcePersistenceJPA().loadAll();
		
		request.setAttribute("ressources", ressources);		
	}
			
	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		
		request.setAttribute("title", "RezRes - Gestion des ressources");
		request.setAttribute("body", "Gestion des ressources");
		request.setAttribute("menu_entry", 4);
		
		String pathInfo= request.getPathInfo();
		
		switch (pathInfo+""){
		case "/delete":
			deleteRessource(request);
			getAllRessources(request);
			request.setAttribute("pano", "active");
			break;
		case "/modify":
			updateRessource(request);
			break;	
		case "/create":
			createRessource(request);
			getAllRessources(request);
			request.setAttribute("create", "active");
			break;
		default:
			getAllRessources(request);
			request.setAttribute("pano", "active");
			break;
		}
		
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		rd = context.getRequestDispatcher(JSP_RES_PATH);
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
}
