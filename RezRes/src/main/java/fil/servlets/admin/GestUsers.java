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

import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.jpa.UtilisateurPersistenceJPA;

 
@WebServlet("/admin/users/*")
public class GestUsers extends HttpServlet {
	/**
	 * URl de la servlet de visualisation des utilisateurs
	 */
	private static String TARGET_PANO = "/JSP/pages/admin/gest_users.jsp";

	private static final long serialVersionUID = -4093378766907157884L;

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher rd;
		
		request.setAttribute("title", "RezRes - Gestion des utilisateurs");
		request.setAttribute("body", "Gestion des utilisateurs");
		request.setAttribute("menu_entry", 5);
		
		rd = getAction(request, response);
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	/**
	 * methode permetant de matcher l'url et de determiner l'action a éxécuter
	 * @param request
	 * @param response
	 * @return RequestDispatcher
	 */
	protected RequestDispatcher getAction(HttpServletRequest request, HttpServletResponse response){
		RequestDispatcher rd;
		ServletContext context = this.getServletContext();
		String target= "/JSP/pages/admin/gest_users.jsp";
		String pathInfo= request.getPathInfo();
		System.out.println(pathInfo);
		
		switch (pathInfo+""){
		case "/delete":
			System.out.println("delete case");
			DeleteAction(request);
			Pano_action(request);
			
			target = TARGET_PANO;
			break;
			
		case "/create":
			System.out.println("create case");
			CreateAction(request);
			Pano_action(request);
			target = TARGET_PANO;
			break;
			
			default:
				target=TARGET_PANO;
				Pano_action(request);
				System.out.println("Cas default");
				break;
		}
		return context.getRequestDispatcher(target);
	}
	/**
	 * methode de creation d'un Utilisateur
	 * @param request
	 */
	private void CreateAction(HttpServletRequest request) {
		System.out.println("create mode");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String telephone = request.getParameter("telephone");
		
	}

	//TODO: verifier que la suppression d'un individu est possible
	public void DeleteAction(HttpServletRequest request){
		UtilisateurPersistenceJPA UtilisateurManager = new UtilisateurPersistenceJPA();
		String identifiant = request.getParameter("id");
		Integer id = Integer.valueOf(identifiant);
		System.out.println("id a supprimé:"+id);
		UtilisateurManager.delete(id);
	
	}
	
	public void Pano_action(HttpServletRequest request){
		List<UtilisateurEntity> entities = new UtilisateurPersistenceJPA().loadAll();
		
		request.setAttribute("Utilisateurs", entities);
	}
}
