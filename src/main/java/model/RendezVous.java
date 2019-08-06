package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mouhamed
 */
@Entity
@Table(name = "rendez_vous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RendezVous.findAll", query = "SELECT r FROM RendezVous r")
    , @NamedQuery(name = "RendezVous.findById", query = "SELECT r FROM RendezVous r WHERE r.id = :id")
    , @NamedQuery(name = "RendezVous.findByLibelle", query = "SELECT r FROM RendezVous r WHERE r.libelle = :libelle")
    , @NamedQuery(name = "RendezVous.findByDate", query = "SELECT r FROM RendezVous r WHERE r.date = :date")})
public class RendezVous implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @ManyToOne
    private Utilisateur iduser;

    public RendezVous() {
    }

    public RendezVous(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getIduser() {
        return iduser;
    }

    public void setIduser(Utilisateur iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RendezVous)) {
            return false;
        }
        RendezVous other = (RendezVous) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.examtest.examtest.entities.RendezVous[ id=" + id + " ]";
    }

}
