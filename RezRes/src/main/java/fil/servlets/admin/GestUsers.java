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
import fil.bean.jpa.UtilisateurEntity;
import fil.persistence.services.jpa.ReservationPersistenceJPA;
import fil.persistence.services.jpa.UtilisateurPersistenceJPA;
import fil.servlets.AdminServlet;


@WebServlet("/admin/users/*")
public class GestUsers extends AdminServlet {
	/**
	 * URl de la servlet de visualisation des utilisateurs
	 */
	private static String TARGET_PANO = "/JSP/pages/admin/gest_users.jsp";
	private static final long serialVersionUID = -4093378766907157884L;

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		super.handleRequest(request, response);
		
		RequestDispatcher rd;

		request.setAttribute("title", "RezRes - Gestion des utilisateurs");
		request.setAttribute("body", "Gestion des utilisateurs");
		request.setAttribute("menu_entry", 5);

		rd = getAction(request, response);
		try
		{
			rd.forward(request, response);
		} 
		catch (IllegalStateException e) {}
	}

	/**
	 * methode permetant de matcher l'url et de determiner l'action a éxécuter
	 * @param request
	 * @param response
	 * @return RequestDispatcher
	 */
	protected RequestDispatcher getAction(HttpServletRequest request, HttpServletResponse response)
	{
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

	private void ModifyAction(HttpServletRequest request) 
	{
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
		else
		{ 
			// on envoie le formulaire 
			request.setAttribute("utilisateur", utilisateur);
			request.setAttribute("modification", true);
		}

	}

	/**
	 * methode de creation d'un Utilisateur
	 * @param request
	 */
	private void CreateAction(HttpServletRequest request) 
	{
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
		else
		{
			System.out.println(dejaExistant.size()+" est deja existant");
			request.setAttribute("loginExistant",true);
		}
	}

	//TODO: verifier que la suppression d'un individu est possible
	public void DeleteAction(HttpServletRequest request)
	{
		UtilisateurPersistenceJPA UtilisateurManager = new UtilisateurPersistenceJPA();
		String identifiant = request.getParameter("id");
		Integer id = Integer.valueOf(identifiant);
		
		ReservationPersistenceJPA res_manager = new ReservationPersistenceJPA();
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("idu", id);
		List<ReservationEntity> res = res_manager.loadByNamedQuery("ReservationEntity.getAllByUser", queryParameters);
		if(res == null || (res != null && res.isEmpty()))
			UtilisateurManager.delete(id);
		else
			request.setAttribute("warning", "Impossible de supprimer l'utilisateur : des réservations y sont associées.");
	}

	public void Pano_action(HttpServletRequest request)
	{
		List<UtilisateurEntity> entities = new UtilisateurPersistenceJPA().loadAll();

		request.setAttribute("Utilisateurs", entities);
	}
}
