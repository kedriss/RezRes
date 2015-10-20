
/*
 * Created on 20 oct. 2015 ( Time 11:40:29 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package fil.mock;

import java.util.LinkedList;
import java.util.List;

import fil.bean.jpa.ReservationEntity;
import fil.mock.tool.MockValues;

public class ReservationEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public ReservationEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public ReservationEntity createInstance( Integer id ) {
		ReservationEntity entity = new ReservationEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setDateDebut( mockValues.nextDate() ) ; // java.util.Date 
		entity.setDateFin( mockValues.nextDate() ) ; // java.util.Date 
		// Init Link fields (if any)
		// setUtilisateur( TODO ) ; // Utilisateur 
		// setRessource( TODO ) ; // Ressource 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<ReservationEntity> createList(int count) {
		List<ReservationEntity> list = new LinkedList<ReservationEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}