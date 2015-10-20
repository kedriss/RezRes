/*
 * Created on 20 oct. 2015 ( Time 11:40:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package fil.persistence.services.fake;

import java.util.List;
import java.util.Map;

import fil.bean.jpa.ReservationEntity;
import fil.persistence.commons.fake.GenericFakeService;
import fil.persistence.services.ReservationPersistence;

/**
 * Fake persistence service implementation ( entity "Reservation" )
 *
 * @author Telosys Tools Generator
 */
public class ReservationPersistenceFAKE extends GenericFakeService<ReservationEntity> implements ReservationPersistence {

	public ReservationPersistenceFAKE () {
		super(ReservationEntity.class);
	}
	
	protected ReservationEntity buildEntity(int index) {
		ReservationEntity entity = new ReservationEntity();
		// Init fields with mock values
		entity.setId( nextInteger() ) ;
		entity.setDateDebut( nextDate() ) ;
		entity.setDateFin( nextDate() ) ;
		return entity ;
	}
	
	
	public boolean delete(ReservationEntity entity) {
		log("delete ( ReservationEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer id ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(ReservationEntity entity) {
		log("insert ( ReservationEntity : " + entity + ")" ) ;
	}

	public ReservationEntity load( Integer id ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<ReservationEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<ReservationEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<ReservationEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public ReservationEntity save(ReservationEntity entity) {
		log("insert ( ReservationEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<ReservationEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}