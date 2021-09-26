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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "users", schema="clinica_veterinaria")
@XmlRootElement
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_cpf")
    private String pessoaCpf;
    @Basic(optional = false)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @JoinColumn(name = "pessoa_cpf", referencedColumnName = "cpf", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public Users() {
    }

    public Users(String pessoaCpf) {
        this.pessoaCpf = pessoaCpf;
    }

    public Users(String pessoaCpf, String matricula, String senha) {
        this.pessoaCpf = pessoaCpf;
        this.matricula = matricula;
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPessoaCpf() {
        return pessoaCpf;
    }

    public void setPessoaCpf(String pessoaCpf) {
        this.pessoaCpf = pessoaCpf;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaCpf != null ? pessoaCpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.pessoaCpf == null && other.pessoaCpf != null) || (this.pessoaCpf != null && !this.pessoaCpf.equals(other.pessoaCpf)));
    }

    @Override
    public String toString() {
        return "matricula: " + matricula 
              +"\nsenha: "   + senha;
    }
    
}
