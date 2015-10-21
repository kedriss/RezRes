package fil.bean.jpa;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-21T13:18:22.960+0200")
@StaticMetamodel(ReservationEntity.class)
public class ReservationEntity_ {
	public static volatile SingularAttribute<ReservationEntity, Integer> id;
	public static volatile SingularAttribute<ReservationEntity, Date> dateDebut;
	public static volatile SingularAttribute<ReservationEntity, Date> dateFin;
	public static volatile SingularAttribute<ReservationEntity, UtilisateurEntity> utilisateur;
	public static volatile SingularAttribute<ReservationEntity, RessourceEntity> ressource;
}
