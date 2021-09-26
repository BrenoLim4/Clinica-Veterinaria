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
 * @author User
 */
@Entity
@Table(name = "exame")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exame.findAll", query = "SELECT e FROM Exame e")
    , @NamedQuery(name = "Exame.findById", query = "SELECT e FROM Exame e WHERE e.id = :id")
    , @NamedQuery(name = "Exame.findByDataHoraSolicitada", query = "SELECT e FROM Exame e WHERE e.dataHoraSolicitada = :dataHoraSolicitada")
    , @NamedQuery(name = "Exame.findByDataHoraResultado", query = "SELECT e FROM Exame e WHERE e.dataHoraResultado = :dataHoraResultado")
    , @NamedQuery(name = "Exame.findByConcluido", query = "SELECT e FROM Exame e WHERE e.concluido = :concluido")
    , @NamedQuery(name = "Exame.findByResultado", query = "SELECT e FROM Exame e WHERE e.resultado = :resultado")
    , @NamedQuery(name = "Exame.findByCusto", query = "SELECT e FROM Exame e WHERE e.custo = :custo")})
public class Exame implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data_hora_solicitada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraSolicitada;
    @Column(name = "data_hora_resultado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraResultado;
    @Basic(optional = false)
    @Column(name = "concluido")
    private short concluido;
    @Column(name = "resultado")
    private String resultado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "custo")
    private BigDecimal custo;
    @JoinColumn(name = "tratamento_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tratamento tratamentoId;

    public Exame() {
    }

    public Exame(Integer id) {
        this.id = id;
    }

    public Exame(Integer id, Date dataHoraSolicitada, short concluido) {
        this.id = id;
        this.dataHoraSolicitada = dataHoraSolicitada;
        this.concluido = concluido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataHoraSolicitada() {
        return dataHoraSolicitada;
    }

    public void setDataHoraSolicitada(Date dataHoraSolicitada) {
        this.dataHoraSolicitada = dataHoraSolicitada;
    }

    public Date getDataHoraResultado() {
        return dataHoraResultado;
    }

    public void setDataHoraResultado(Date dataHoraResultado) {
        this.dataHoraResultado = dataHoraResultado;
    }

    public short getConcluido() {
        return concluido;
    }

    public void setConcluido(short concluido) {
        this.concluido = concluido;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
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
        if (!(object instanceof Exame)) {
            return false;
        }
        Exame other = (Exame) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "clinicaveterinaria.Entidades.Exame[ id=" + id + " ]";
    }
    
}
