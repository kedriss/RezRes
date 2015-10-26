/*
 * Created on 23 oct. 2015 ( Time 12:09:27 )
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
 * Persistent class for entity stored in table "RESSOURCE"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="RESSOURCE", schema="PUBLIC", catalog="REZRES" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="RessourceEntity.countAll", query="SELECT COUNT(x) FROM RessourceEntity x" ),
  @NamedQuery ( name="RessourceEntity.getFreeRessource", query="SELECT x FROM RessourceEntity x where x.typeRessource.cle = :type "
				+ " AND NOT EXISTS (SELECT 1 FROM ReservationEntity y WHERE y.ressource = x "
				+ " AND (:date_debut BETWEEN y.dateDebut and y.dateFin OR :date_fin BETWEEN y.dateDebut and y.dateFin"
				+ " OR y.dateDebut BETWEEN :date_debut and :date_fin  OR y.dateFin BETWEEN :date_debut and :date_fin))" ),
  @NamedQuery ( name="RessourceEntity.getByType", query="SELECT r FROM RessourceEntity r WHERE r.typeRessource.cle = :id" )
} )
public class RessourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ID", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="NOM", nullable=false, length=255)
    private String     nom          ;

    @Column(name="DESCRIPTION", nullable=false, length=255)
    private String     description  ;

    @Column(name="RESPONSABLE", nullable=false)
    private Integer    responsable  ;

    @Column(name="LOCALITE", nullable=false, length=255)
    private String     localite     ;

	// "type" (column "TYPE") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="TYPE", referencedColumnName="CLE")
    private TypeRessourceEntity typeRessource;

    @OneToMany(mappedBy="ressource", targetEntity=ReservationEntity.class)
    private List<ReservationEntity> listOfReservation;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public RessourceEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : NOM ( VARCHAR ) 
    public void setNom( String nom ) {
        this.nom = nom;
    }
    public String getNom() {
        return this.nom;
    }

    //--- DATABASE MAPPING : DESCRIPTION ( VARCHAR ) 
    public void setDescription( String description ) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    //--- DATABASE MAPPING : RESPONSABLE ( INTEGER ) 
    public void setResponsable( Integer responsable ) {
        this.responsable = responsable;
    }
    public Integer getResponsable() {
        return this.responsable;
    }

    //--- DATABASE MAPPING : LOCALITE ( VARCHAR ) 
    public void setLocalite( String localite ) {
        this.localite = localite;
    }
    public String getLocalite() {
        return this.localite;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setTypeRessource( TypeRessourceEntity typeRessource ) {
        this.typeRessource = typeRessource;
    }
    public TypeRessourceEntity getTypeRessource() {
        return this.typeRessource;
    }

    public void setListOfReservation( List<ReservationEntity> listOfReservation ) {
        this.listOfReservation = listOfReservation;
    }
    public List<ReservationEntity> getListOfReservation() {
        return this.listOfReservation;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(nom);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(responsable);
        sb.append("|");
        sb.append(localite);
        return sb.toString(); 
    } 

}
