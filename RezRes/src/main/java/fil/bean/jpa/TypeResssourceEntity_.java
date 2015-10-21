package fil.bean.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-21T13:18:23.082+0200")
@StaticMetamodel(TypeResssourceEntity.class)
public class TypeResssourceEntity_ {
	public static volatile SingularAttribute<TypeResssourceEntity, Integer> cle;
	public static volatile SingularAttribute<TypeResssourceEntity, String> libelle;
	public static volatile ListAttribute<TypeResssourceEntity, RessourceEntity> listOfRessource;
}
