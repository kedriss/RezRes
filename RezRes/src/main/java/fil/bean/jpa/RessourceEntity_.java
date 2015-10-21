package fil.bean.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-21T13:18:23.081+0200")
@StaticMetamodel(RessourceEntity.class)
public class RessourceEntity_ {
	public static volatile SingularAttribute<RessourceEntity, Integer> id;
	public static volatile SingularAttribute<RessourceEntity, String> nom;
	public static volatile SingularAttribute<RessourceEntity, String> desc;
	public static volatile SingularAttribute<RessourceEntity, Integer> responsable;
	public static volatile SingularAttribute<RessourceEntity, String> localite;
	public static volatile SingularAttribute<RessourceEntity, TypeResssourceEntity> typeResssource;
	public static volatile ListAttribute<RessourceEntity, ReservationEntity> listOfReservation;
}
