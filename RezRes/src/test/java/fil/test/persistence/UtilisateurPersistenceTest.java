/*
 * Created on 26 oct. 2015 ( Time 19:14:45 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package fil.test.persistence;


import org.junit.Assert;
import org.junit.Test;

import fil.bean.jpa.UtilisateurEntity;
import fil.mock.UtilisateurEntityMock;
import fil.persistence.PersistenceServiceProvider;
import fil.persistence.services.UtilisateurPersistence;

/**
 * JUnit test case for Utilisateur persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class UtilisateurPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		UtilisateurPersistence service = PersistenceServiceProvider.getService(UtilisateurPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Utilisateur persistence : delete + load ..." );
		
		UtilisateurPersistence service = PersistenceServiceProvider.getService(UtilisateurPersistence.class);
		
		UtilisateurEntityMock mock = new UtilisateurEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(UtilisateurPersistence service, UtilisateurEntityMock mock, Integer id ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		UtilisateurEntity entity = service.load( id );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( id ) ;
			Assert.assertNotNull(entity);

			// No reference : insert is possible 
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );

			System.out.println(" . delete : " );
			boolean deleted = service.delete( id );
			System.out.println("   deleted = " + deleted);
			Assert.assertTrue(deleted) ;
		}		
	}
}
