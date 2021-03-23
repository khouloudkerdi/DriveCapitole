/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neopro.metier;

import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author khoul
 */
@Entity
public class Stock {
    
    // Propriétés.
    @EmbeddedId
    private StockID id;
    private int quantiteSto;
    
    //Constructeurs.

    public Stock() {
    }

    public Stock(int quantiteSto) {
        this.quantiteSto = quantiteSto;
    }
    
    //Getters /Setters.

    public StockID getId() {return id;}
    public void setId(StockID id) { this.id = id;}
    public int getQuantiteSto() {return quantiteSto;}
    public void setQuantiteSto(int quantiteSto) { this.quantiteSto = quantiteSto;}
    
    //Methodes.
    //Methodes Surchargées.

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", quantiteSto=" + quantiteSto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Stock other = (Stock) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
  
    
}
