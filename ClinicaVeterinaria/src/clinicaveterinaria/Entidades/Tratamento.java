/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Entidades;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "tratamento", schema="clinica_veterinaria")
@XmlRootElement
public class Tratamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;    
    @JoinColumn(name = "medico_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Pessoa medico;
    @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Pessoa acompanhante;
    @JoinColumn(name="animal_id", referencedColumnName = "id")
    @ManyToOne()
    private Animal paciente; 
    
    
    public Tratamento() {
    }

    public Tratamento(Integer id) {
        this.id = id;
    }

    public Pessoa getMedico() {
        return medico;
    }

    public Pessoa getAcompanhante() {
        return acompanhante;
    }

    public Animal getPaciente() {
        return paciente;
    }

    public Integer getId() {
        return id;
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
        if (!(object instanceof Tratamento)) {
            return false;
        }
        Tratamento other = (Tratamento) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "clinicaveterinaria.Entidades.Tratamento[ id=" + id + " ]";
    }
    
}
