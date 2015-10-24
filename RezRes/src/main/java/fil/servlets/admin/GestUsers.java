package fil.servlets.admin;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.jpa.UtilisateurPersistenceJPA;

 
@WebServlet("/admin/users/*")
public class GestUsers extends HttpServlet {
	/**
	 * URl de la servlet de visualisation des utilisateurs
	 */
	private static String TARGET_PANO = "/JSP/pages/admin/gest_users.jsp";
	private static String MOT_DE_PASSE = "pwd";
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
		//System.out.println(pathInfo);
		
		switch (pathInfo+""){
		case "/delete":
			System.out.println("delete case");
			DeleteAction(request);
			Pano_action(request);
			request.setAttribute("pano", "active");
			target = TARGET_PANO;
			break;
			
		case "/modify":
			System.out.println("modify case");
			ModifyAction(request);
			target = TARGET_PANO;
			break;
			
			
		case "/create":
			System.out.println("create case");
			CreateAction(request);
			Pano_action(request);
			request.setAttribute("create", "active");
			target = TARGET_PANO;
			break;
			
			default:
				target=TARGET_PANO;
				Pano_action(request);
				request.setAttribute("pano", "active");
				System.out.println("Cas default");
				break;
		}
		return context.getRequestDispatcher(target);
	}
	private void ModifyAction(HttpServletRequest request) {
		String id    = request.getParameter("id");
		String login = request.getParameter("login");
		UtilisateurPersistenceJPA UtilisateurManager = new UtilisateurPersistenceJPA();
		UtilisateurEntity utilisateur = UtilisateurManager.load(Integer.valueOf(id));
		
		if(login!= null){
			String nom 		= request.getParameter("nom");
			String prenom 	= request.getParameter("prenom");
			String mail 	= request.getParameter("mail");
			String telephone= request.getParameter("telephone");
			String type 	= request.getParameter("type");
		// on met a jour l'utilisateur
			utilisateur.setType(Integer.valueOf(type));
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setLogin(login);
			utilisateur.setMail(mail);
			utilisateur.setTelephone(telephone);
			
			UtilisateurManager.save(utilisateur);
			
			request.setAttribute("modifOK", true);
			Pano_action(request);
			request.setAttribute("pano", "active");
		}
		else{ // on envoie le formulaire 
			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("modification", true);
		}
		
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
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");
		String type = request.getParameter("type");
		UtilisateurPersistenceJPA UtilisateurManager = new UtilisateurPersistenceJPA();
		Map<String, Object> map=new HashMap<String, Object> ();
		map.put("login", login);
		List<UtilisateurEntity> dejaExistant = UtilisateurManager.search(map);//("UtilisateurEntity.selectLogin", map);
		if(dejaExistant.isEmpty()){
			
			
		UtilisateurEntity utilisateur = new UtilisateurEntity();
		
		
		System.out.println(nom+"/"+prenom+"/"+mail+"/"+login+"/"+pwd+"/"+telephone);
		
		utilisateur.setType(Integer.valueOf(type));
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setMail(mail);
		utilisateur.setLogin(login);
		utilisateur.setPwd(pwd);
		utilisateur.setTelephone(telephone);
		UtilisateurManager.insert(utilisateur);
		request.setAttribute("loginCreer",true);
		}
		else{
			System.out.println(dejaExistant.size()+" est deja existant");
			request.setAttribute("loginExistant",true);}
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
