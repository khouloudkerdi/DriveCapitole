/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author khoul
 */
@Entity
public class DateCreneau {
    //Proprietes.
    @Id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date; 
    
    //relation Proposer
    @OneToMany(mappedBy = "date", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idMag")
    private Map<Magasin, Proposer> creneaux = new HashMap<>();
     
    @OneToMany(mappedBy = "date", cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "idCre")
    private Map<Creneau, Proposer> dates = new HashMap<>();
    //Constructeurs.

    public DateCreneau() {
    }

    public DateCreneau(Date date) {
        this.date = date;
    }

    
    //Getters /Setters.
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Magasin, Proposer> getCreneaux() {
        return creneaux;
    }
    public void setDates(Map<Creneau, Proposer> dates) {
        this.dates = dates;
    }
    public Map<Creneau, Proposer> getDates() {
        return dates;
    }

    public void setCreneaux(Map<Magasin, Proposer> creneaux) {
        this.creneaux = creneaux;
    }
    
    //Methodes Surchargés.

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DateCreneau other = (DateCreneau) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
}
