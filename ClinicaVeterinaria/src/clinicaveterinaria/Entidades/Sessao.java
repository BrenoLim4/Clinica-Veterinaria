/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Breno
 */
@Entity
@Table(name = "sessao", schema="clinica_veterinaria")
@XmlRootElement
public class Sessao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "sintomas")
    private String sintomas;
    @Column(name = "diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @Column(name = "data_Marcada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMarcada;
    @Column(name = "data_encerramento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEncerramento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "custo")
    private BigDecimal custo;
    @Column(name = "gravidade_id")
    private Integer gravidadeId;
    @Column(name = "status_sessao_id")
    private Integer statusSessaoId;
    @JoinColumn(name = "tratamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tratamento tratamentoId;

    public Sessao() {
    }

    public Sessao(Integer id) {
        this.id = id;
    }

    public Sessao(Integer id, String codigo, Date dataMarcada) {
        this.id = id;
        this.codigo = codigo;
        this.dataMarcada = dataMarcada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getDataMarcada() {
        return dataMarcada;
    }

    public void setDataMarcada(Date dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public Integer getGravidadeId() {
        return gravidadeId;
    }

    public void setGravidadeId(Integer gravidadeId) {
        this.gravidadeId = gravidadeId;
    }

    public Integer getStatusSessaoId() {
        return statusSessaoId;
    }

    public void setStatusSessaoId(Integer statusSessaoId) {
        this.statusSessaoId = statusSessaoId;
    }

    public Tratamento getTratamentoId() {
        return tratamentoId;
    }

    public void setTratamentoId(Tratamento tratamentoId) {
        this.tratamentoId = tratamentoId;
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
        if (!(object instanceof Sessao)) {
            return false;
        }
        Sessao other = (Sessao) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "clinicaveterinaria.Entidades.Sessao[ id=" + id + " ]";
    }
    
}
