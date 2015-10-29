package fil.util;

import javax.servlet.http.HttpServletRequest;
/**
 * Enum�ration des messages
 * @author cedric
 *
 */
public enum Messages {
		// USER	
		USERCREATED			("<strong>OK!</strong> Utilisateur correctement cr��.","success"),
		USERCREATIONDENIED	("<strong>Refus�!</strong> Ce login est d�j� pris.","danger"),
		USERDELETEDENIED 	("Impossible de supprimer l'utilisateur : des r�servations y sont associ�es.","warning"),
		USERMODIFIED		("<strong>OK!</strong> utilisateur modifi�e.","success"),
		// RESSOURCE
		RESSOURCECREATED 	("<strong>OK!</strong> Ressource cr��e.","success"),
		RESSOURCEDELETED 	("<strong>OK!</strong> Ressource supprim�e.","success"),
		RESSOURCEDELETEDENIED("<strong>Refus�!</strong> des r�servations sont associ�es � cette ressource.","warning"),
		RESSOURCEMODIFIED	("<strong>OK!</strong> Ressource modifi�e.","success"),
		//TYPERESSOURCE 
		TypeRessourceModifyDeniedID		("<strong>Refus�!</strong>Impossible de modifi� : pas de clef pr�cis�.","warning"),
		BADTypeRessource				("<strong>Refus�!</strong>Vous n'avez pas pr�cis� de type.","warning"),
		TypeRessourceCreationDeniedName	("<strong>Refus�!</strong>Impossible de cr�er le type demand� : pas de nom.","warning"),
		TypeRessourceDeleteDenied  		("<strong>Refus�!</strong> Des ressources sont associ�es � ce type.","warning"),
		TypeRessourceCreated 			("<strong>OK!</strong> Type ajout�.","success"),
		TypeRessourceModified			("<strong>OK!</strong> Type modifi�.","success"),
		
		// RESEVATION
		ReservationBadDate				("<strong>Refus�!</strong> Les dates ne sont pas coh�rentes.","danger"),
		
		//GLOBAL
		InputsEmpty		("Tous les champs doivent �tre saisies","warning"),
		BadDateFormat	("La date doit �tre au format 'yyyy-MM-dd' et l'heure au format 'HH:mm'","warning");
			
	
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
