package fil.bean.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-21T13:18:23.084+0200")
@StaticMetamodel(UtilisateurEntity.class)
public class UtilisateurEntity_ {
	public static volatile SingularAttribute<UtilisateurEntity, Integer> id;
	public static volatile SingularAttribute<UtilisateurEntity, String> login;
	public static volatile SingularAttribute<UtilisateurEntity, String> pwd;
	public static volatile SingularAttribute<UtilisateurEntity, String> nom;
	public static volatile SingularAttribute<UtilisateurEntity, String> prenom;
	public static volatile SingularAttribute<UtilisateurEntity, String> mail;
	public static volatile SingularAttribute<UtilisateurEntity, String> telephone;
	public static volatile SingularAttribute<UtilisateurEntity, Integer> type;
	public static volatile ListAttribute<UtilisateurEntity, ReservationEntity> listOfReservation;
}
