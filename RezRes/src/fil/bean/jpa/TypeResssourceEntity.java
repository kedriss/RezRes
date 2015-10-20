/*
 * Created on 20 oct. 2015 ( Time 11:40:31 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package fil.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "TYPE_RESSSOURCE"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="TYPE_RESSSOURCE", schema="PUBLIC", catalog="REZRES" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="TypeResssourceEntity.countAll", query="SELECT COUNT(x) FROM TypeResssourceEntity x" )
} )
public class TypeResssourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="CLE", nullable=false)
    private Integer    cle          ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="LIBELLE", nullable=false, length=255)
    private String     libelle      ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="typeResssource", targetEntity=RessourceEntity.class)
    private List<RessourceEntity> listOfRessource;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public TypeResssourceEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCle( Integer cle ) {
        this.cle = cle ;
    }
    public Integer getCle() {
        return this.cle;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : LIBELLE ( VARCHAR ) 
    public void setLibelle( String libelle ) {
        this.libelle = libelle;
    }
    public String getLibelle() {
        return this.libelle;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfRessource( List<RessourceEntity> listOfRessource ) {
        this.listOfRessource = listOfRessource;
    }
    public List<RessourceEntity> getListOfRessource() {
        return this.listOfRessource;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(cle);
        sb.append("]:"); 
        sb.append(libelle);
        return sb.toString(); 
    } 

}