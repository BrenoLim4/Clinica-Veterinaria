/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.EntidadesLogicas;

import clinicaveterinaria.Entidades.Animal;
import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.Medico;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author BrenoLima
 */
public class Exame {
    private Animal  paciente;
    private Cliente responsavel;
    private Medico  solicitante;
    private Long    dataHoraSolicitacao;
    private Date    dataHoraResultado;
    private Boolean encerrada = false;
    private String  resultado;
    
    public Exame(Animal animal, Cliente cliente, Medico medico) {
        this.solicitante    = medico;
        this.paciente       = animal;
        this.responsavel    = cliente;
        dataHoraSolicitacao = new Date().getTime();        
    }
    public Exame(){
        
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Exame other = (Exame) obj;
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.responsavel, other.responsavel)) {
            return false;
        }
        if (!Objects.equals(this.solicitante, other.solicitante)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraSolicitacao, other.dataHoraSolicitacao)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraResultado, other.dataHoraResultado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exame{" + "paciente=" + paciente + ", responsavel=" + responsavel + ", solicitante=" + solicitante + ", dataHoraSolicitacao=" + dataHoraSolicitacao + ", dataHoraResultado=" + dataHoraResultado + '}';
    }

    public Animal getPaciente() {
        return paciente;
    }

    public void setPaciente(Animal paciente) {
        this.paciente = paciente;
    }

    public Cliente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Cliente responsavel) {
        this.responsavel = responsavel;
    }

    public Medico getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Medico solicitante) {
        this.solicitante = solicitante;
    }

    public Long getDataHoraSolicitacao() {
        return dataHoraSolicitacao;
    }

    public void setDataHoraSolicitacao(Long dataHoraSolicitacao) {
        this.dataHoraSolicitacao = dataHoraSolicitacao;
    }

    public Date getDataHoraResultado() {
        return dataHoraResultado;
    }

    public void setDataHoraResultado(Date dataHoraResultado) {
        this.dataHoraResultado = dataHoraResultado;
    }

    public Boolean getEncerrada() {
        return encerrada;
    }

    public void setEncerrada(Boolean encerrada) {
        this.encerrada = encerrada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}
