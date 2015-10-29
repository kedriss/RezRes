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
		USERDELETEDENIED 	("<strong>Refusé!</strong>Impossible de supprimer l'utilisateur : des réservations y sont associées.","danger"),
		USERMODIFIED		("<strong>OK!</strong> Utilisateur modifié.","success"),
		// RESSOURCE
		RESSOURCECREATED 	("<strong>OK!</strong> Ressource créée.","success"),
		RESSOURCEDELETED 	("<strong>OK!</strong> Ressource supprimée.","success"),
		RESSOURCEDELETEDENIED("<strong>Refusé!</strong> Des réservations sont associées à cette ressource.","danger"),
		RESSOURCEMODIFIED	("<strong>OK!</strong> Ressource modifiée.","success"),
		//TYPERESSOURCE 
		TypeRessourceModifyDeniedID		("<strong>Refusé!</strong> Impossible de modifié : pas de clef précisé.","danger"),
		BADTypeRessource				("<strong>Refusé!</strong> Vous n'avez pas précisé de type.","danger"),
		TypeRessourceCreationDeniedName	("<strong>Refusé!</strong> Impossible de créer le type demandé : pas de nom.","danger"),
		TypeRessourceDeleteDenied  		("<strong>Refusé!</strong> Des reservations sont associées à ce type.","danger"),
		TypeRessourceCreated 			("<strong>OK!</strong> Type ajouté.","success"),
		TypeRessourceModified			("<strong>OK!</strong> Type modifié.","success"),
		
		// RESEVATION
		ReservationBadDate				("<strong>Refusé!</strong> Les dates ne sont pas cohérentes.","danger"),
		
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
