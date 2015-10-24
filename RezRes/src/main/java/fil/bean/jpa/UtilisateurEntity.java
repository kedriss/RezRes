/*
 * Created on 23 oct. 2015 ( Time 22:28:40 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package fil.bean.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;
import java.util.List;

/**
 * Persistent class for entity stored in table "UTILISATEUR"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="UTILISATEUR", schema="PUBLIC", catalog="REZRES" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="UtilisateurEntity.countAll", query="SELECT COUNT(x) FROM UtilisateurEntity x" ),
  @NamedQuery ( name="UtilisateurEntity.selectLogin", query=" SELECT x FROM UtilisateurEntity x where x.login = :login" )
} )
public class UtilisateurEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="LOGIN", nullable=false, length=255)
    private String     login        ;

    @Column(name="PWD", nullable=false, length=255)
    private String     pwd          ;

    @Column(name="NOM", nullable=false, length=255)
    private String     nom          ;

    @Column(name="PRENOM", nullable=false, length=255)
    private String     prenom       ;

    @Column(name="MAIL", nullable=false, length=255)
    private String     mail         ;

    @Column(name="TELEPHONE", length=255)
    private String     telephone    ;

    @Column(name="TYPE", nullable=false)
    private Integer    type         ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @OneToMany(mappedBy="utilisateur", targetEntity=ReservationEntity.class)
    private List<ReservationEntity> listOfReservation;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public UtilisateurEntity() {
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
    //--- DATABASE MAPPING : LOGIN ( VARCHAR ) 
    public void setLogin( String login ) {
        this.login = login;
    }
    public String getLogin() {
        return this.login;
    }

    //--- DATABASE MAPPING : PWD ( VARCHAR ) 
    public void setPwd( String pwd ) {
        this.pwd = pwd;
    }
    public String getPwd() {
        return this.pwd;
    }

    //--- DATABASE MAPPING : NOM ( VARCHAR ) 
    public void setNom( String nom ) {
        this.nom = nom;
    }
    public String getNom() {
        return this.nom;
    }

    //--- DATABASE MAPPING : PRENOM ( VARCHAR ) 
    public void setPrenom( String prenom ) {
        this.prenom = prenom;
    }
    public String getPrenom() {
        return this.prenom;
    }

    //--- DATABASE MAPPING : MAIL ( VARCHAR ) 
    public void setMail( String mail ) {
        this.mail = mail;
    }
    public String getMail() {
        return this.mail;
    }

    //--- DATABASE MAPPING : TELEPHONE ( VARCHAR ) 
    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return this.telephone;
    }

    //--- DATABASE MAPPING : TYPE ( INTEGER ) 
    public void setType( Integer type ) {
        this.type = type;
    }
    public Integer getType() {
        return this.type;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
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
        sb.append(login);
        sb.append("|");
        sb.append(pwd);
        sb.append("|");
        sb.append(nom);
        sb.append("|");
        sb.append(prenom);
        sb.append("|");
        sb.append(mail);
        sb.append("|");
        sb.append(telephone);
        sb.append("|");
        sb.append(type);
        return sb.toString(); 
    } 

}
