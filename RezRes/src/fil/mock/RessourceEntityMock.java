
/*
 * Created on 20 oct. 2015 ( Time 11:40:30 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package fil.mock;

import java.util.LinkedList;
import java.util.List;

import fil.bean.jpa.RessourceEntity;
import fil.mock.tool.MockValues;

public class RessourceEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public RessourceEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public RessourceEntity createInstance( Integer id ) {
		RessourceEntity entity = new RessourceEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setNom( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setDesc( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setResponsable( mockValues.nextInteger() ) ; // java.lang.Integer 
		entity.setLocalite( mockValues.nextString(255) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setTypeResssource( TODO ) ; // TypeResssource 
		// setListOfReservation( TODO ) ; // List<Reservation> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<RessourceEntity> createList(int count) {
		List<RessourceEntity> list = new LinkedList<RessourceEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
