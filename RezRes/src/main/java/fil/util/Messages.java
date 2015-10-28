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
		RESSOURCEDELETED 	("<strong>OK!</strong> Ressource cr��e.","success"),
		RESSOURCEDELETEDENIED("Impossible de supprimer la ressource : des r�servations y sont associ�es.","warning"),
		RESSOURCEMODIFIED	("<strong>OK!</strong> Ressource modifi�e.","success"),
		//TYPERESSOURCE 
		TypeRessourceModifyDeniedID		("Impossible de modifi� : pas de clef pr�cis�.","warning"),
		BADTypeRessource				("Vous n'avez pas pr�cis� de type.","warning"),
		TypeRessourceCreationDeniedName	("Impossible de cr�er le type demand� : pas de nom.","warning"),
		TypeRessourceDeleteDenied  		("Impossible de supprimer le type : des ressources y sont associ�es.","warning"),
		
		
		
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
