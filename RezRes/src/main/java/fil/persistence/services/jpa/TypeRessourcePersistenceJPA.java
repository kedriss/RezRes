/*
 * Created on 26 oct. 2015 ( Time 19:14:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package fil.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import fil.bean.jpa.TypeRessourceEntity;
import fil.persistence.commons.jpa.GenericJpaService;
import fil.persistence.commons.jpa.JpaOperation;
import fil.persistence.services.TypeRessourcePersistence;

/**
 * JPA implementation for basic persistence operations ( entity "TypeRessource" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class TypeRessourcePersistenceJPA extends GenericJpaService<TypeRessourceEntity, Integer> implements TypeRessourcePersistence {

	/**
	 * Constructor
	 */
	public TypeRessourcePersistenceJPA() {
		super(TypeRessourceEntity.class);
	}

	@Override
	public TypeRessourceEntity load( Integer cle ) {
		return super.load( cle );
	}

	@Override
	public boolean delete( Integer cle ) {
		return super.delete( cle );
	}

	@Override
	public boolean delete(TypeRessourceEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getCle() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("TypeRessourceEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
