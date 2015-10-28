package fil.util;

import javax.servlet.http.HttpServletRequest;
/**
 * Enumération des messages
 * @author cedric
 *
 */
public enum Messages {
		// USER	
		USERCREATED			("<strong>OK!</strong> Utilisateur correctement créé.","success"),
		USERCREATIONDENIED	("<strong>Refusé!</strong> Ce login est déjà pris.","danger"),
		USERDELETEDENIED 	("Impossible de supprimer l'utilisateur : des réservations y sont associées.","warning"),
		USERMODIFIED		("<strong>OK!</strong> utilisateur modifiée.","success"),
		// RESSOURCE
		RESSOURCECREATED 	("<strong>OK!</strong> Ressource créée.","success"),
		RESSOURCEDELETED 	("<strong>OK!</strong> Ressource créée.","success"),
		RESSOURCEDELETEDENIED("Impossible de supprimer la ressource : des réservations y sont associées.","warning"),
		RESSOURCEMODIFIED	("<strong>OK!</strong> Ressource modifiée.","success"),
		//TYPERESSOURCE 
		TypeRessourceModifyDeniedID		("Impossible de modifié : pas de clef précisé.","warning"),
		BADTypeRessource				("Vous n'avez pas précisé de type.","warning"),
		TypeRessourceCreationDeniedName	("Impossible de créer le type demandé : pas de nom.","warning"),
		TypeRessourceDeleteDenied  		("Impossible de supprimer le type : des ressources y sont associées.","warning"),
		
		
		
		//GLOBAL
		InputsEmpty		("Tous les champs doivent être saisies","warning"),
		BadDateFormat	("La date doit être au format 'yyyy-MM-dd' et l'heure au format 'HH:mm'","warning");
			
	
	private String message;
	private String messageType;

	private Messages(String message, String messageType){
		
		this.message= message;
		this.messageType = messageType;
	};
	
	public void setMessage(HttpServletRequest request){
		
		request.setAttribute("message", this.message);
		request.setAttribute("messageType",this.messageType);
	}
}
