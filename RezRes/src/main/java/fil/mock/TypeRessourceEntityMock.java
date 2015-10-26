
/*
 * Created on 26 oct. 2015 ( Time 19:14:44 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package fil.mock;

import java.util.LinkedList;
import java.util.List;

import fil.bean.jpa.TypeRessourceEntity;
import fil.mock.tool.MockValues;

public class TypeRessourceEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public TypeRessourceEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public TypeRessourceEntity createInstance( Integer cle ) {
		TypeRessourceEntity entity = new TypeRessourceEntity();
		// Init Primary Key fields
		entity.setCle( cle) ;
		// Init Data fields
		entity.setLibelle( mockValues.nextString(255) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setListOfRessource( TODO ) ; // List<Ressource> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<TypeRessourceEntity> createList(int count) {
		List<TypeRessourceEntity> list = new LinkedList<TypeRessourceEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
