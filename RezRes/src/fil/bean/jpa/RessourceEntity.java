/*
 * Created on 20 oct. 2015 ( Time 11:40:30 )
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
  @NamedQuery ( name="RessourceEntity.countAll", query="SELECT COUNT(x) FROM RessourceEntity x" )
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

    @Column(name="desc", nullable=false, length=255)
    private String     desc         ;

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
    private TypeResssourceEntity typeResssource;

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

    //--- DATABASE MAPPING : desc ( VARCHAR ) 
    public void setDesc( String desc ) {
        this.desc = desc;
    }
    public String getDesc() {
        return this.desc;
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
    public void setTypeResssource( TypeResssourceEntity typeResssource ) {
        this.typeResssource = typeResssource;
    }
    public TypeResssourceEntity getTypeResssource() {
        return this.typeResssource;
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
        sb.append(desc);
        sb.append("|");
        sb.append(responsable);
        sb.append("|");
        sb.append(localite);
        return sb.toString(); 
    } 

}
