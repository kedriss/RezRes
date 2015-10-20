/*
 * Created on 20 oct. 2015 ( Time 11:40:28 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package fil.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "RESERVATION"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="RESERVATION", schema="PUBLIC", catalog="REZRES" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="ReservationEntity.countAll", query="SELECT COUNT(x) FROM ReservationEntity x" )
} )
public class ReservationEntity implements Serializable {

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
    @Temporal(TemporalType.DATE)
    @Column(name="DATE_DEBUT", nullable=false)
    private Date       dateDebut    ;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_FIN", nullable=false)
    private Date       dateFin      ;

	// "idRessource" (column "ID_RESSOURCE") is not defined by itself because used as FK in a link 
	// "idUtilisateur" (column "ID_UTILISATEUR") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="ID_UTILISATEUR", referencedColumnName="ID")
    private UtilisateurEntity utilisateur ;

    @ManyToOne
    @JoinColumn(name="ID_RESSOURCE", referencedColumnName="ID")
    private RessourceEntity ressource   ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public ReservationEntity() {
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
    //--- DATABASE MAPPING : DATE_DEBUT ( DATE ) 
    public void setDateDebut( Date dateDebut ) {
        this.dateDebut = dateDebut;
    }
    public Date getDateDebut() {
        return this.dateDebut;
    }

    //--- DATABASE MAPPING : DATE_FIN ( DATE ) 
    public void setDateFin( Date dateFin ) {
        this.dateFin = dateFin;
    }
    public Date getDateFin() {
        return this.dateFin;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setUtilisateur( UtilisateurEntity utilisateur ) {
        this.utilisateur = utilisateur;
    }
    public UtilisateurEntity getUtilisateur() {
        return this.utilisateur;
    }

    public void setRessource( RessourceEntity ressource ) {
        this.ressource = ressource;
    }
    public RessourceEntity getRessource() {
        return this.ressource;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(dateDebut);
        sb.append("|");
        sb.append(dateFin);
        return sb.toString(); 
    } 

}
