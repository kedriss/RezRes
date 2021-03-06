
/*
 * Created on 26 oct. 2015 ( Time 19:14:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package fil.mock;

import java.util.LinkedList;
import java.util.List;

import fil.bean.jpa.UtilisateurEntity;
import fil.mock.tool.MockValues;

public class UtilisateurEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public UtilisateurEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public UtilisateurEntity createInstance( Integer id ) {
		UtilisateurEntity entity = new UtilisateurEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setLogin( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setPwd( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setNom( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setPrenom( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setMail( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setTelephone( mockValues.nextString(255) ) ; // java.lang.String 
		entity.setType( mockValues.nextInteger() ) ; // java.lang.Integer 
		// Init Link fields (if any)
		// setListOfRessource( TODO ) ; // List<Ressource> 
		// setListOfReservation( TODO ) ; // List<Reservation> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<UtilisateurEntity> createList(int count) {
		List<UtilisateurEntity> list = new LinkedList<UtilisateurEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
