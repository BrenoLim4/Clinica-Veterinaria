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
import java.util.Random;

/**
 *
 * @author BrenoLima
 */
public class Sessao extends Tratamento {
    //CONSTANTES
    private static final int GRAVIDADE_BAIXA   = 1;
    private static final int GRAVIDADE_MEDIA   = 2;
    private static final int GRAVIDADE_ALTA    = 3;
    
    private static final int STATUS_REGISTRADA = 4;
    private static final int STATUS_CANCELADA  = 5;
    private static final int STATUS_FINALIZADA = 6;
    
    //
    //
    private final Random gerador        = new Random();
    private String       codigoSessao   = "";
    private Integer      codigoTratamento;
    private Boolean      encerrada      = false; 
    private int          status         = STATUS_REGISTRADA;
    private Boolean      examePendente  = false;
    private String       Sintomas;
    private int          gravidade;
    private String       diagnostico;
    private Date         dataMarcada;
    private Date         dataEncerramento;
    
    /**
     *
     * @param cliente
     * @param animal
     * @param medico
     */
    public Sessao(Cliente cliente, Animal animal, Medico medico) {
        super(cliente, animal, medico);
    }    
    
    public Sessao(){
        
    }
    
    {
        int anoConsulta =  new Date().getYear();
        codigoSessao =  String.valueOf(gerador.nextInt());
        codigoSessao = codigoSessao.substring(0,2).concat(String.valueOf(anoConsulta));
    }    



    @Override
    public int hashCode() {
        int hash = 5;
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
        final Sessao other = (Sessao) obj;
        if (!Objects.equals(this.getMedico(), other.getMedico())) {
            return false;
        }
        if (!Objects.equals(this.getCliente(), other.getCliente())) {
            return false;
        }
        return Objects.equals(this.getPaciente(), other.getPaciente());
    }

    @Override
    public String toString() {
        return "Sessao{"
                + "\nCodigo Sessão: " + codigoSessao 
                + "\nPaciente: "      + super.getPaciente().getNome()
                + "\nCliente: "       + super.getCliente().getNome()
                + "\nMedico: "        + super.getMedico().getNome()
                + "\nSolicitaExame: " + (examePendente == true ? "Sim" : "Não")
                + "\nSintomas: "      + Sintomas + "  }";
    }

    public static int getGRAVIDADE_BAIXA() {
        return GRAVIDADE_BAIXA;
    }

    public static int getGRAVIDADE_MEDIA() {
        return GRAVIDADE_MEDIA;
    }

    public static int getGRAVIDADE_ALTA() {
        return GRAVIDADE_ALTA;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String Sintomas) {
        this.Sintomas = Sintomas;
    }

    public String getCodigoSessao() {
        return codigoSessao;
    }

    public void setDataMarcada(Date dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public Date getDataMarcada() {
        return dataMarcada;
    }

    public Boolean isEncerrada() {
        return encerrada;
    }

    public void setEncerrada(Boolean encerrada) {
        this.encerrada = encerrada;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }
    public void setStatus(int status){
        this.status = status; 
    }
    public String getStatusExtenso(){
        switch(status){
            case STATUS_REGISTRADA:
                return "Registrada";
            case STATUS_CANCELADA:
                return "Cancelada";
            case STATUS_FINALIZADA:
                return "Finalizada";
            default:
                return "";
        }
    }

    public int getStatus() {
        return status;
    }

    public Integer getCodigoTratamento() {
        return codigoTratamento;
    }

    public void setCodigoTratamento(Integer codigoTratamento) {
        this.codigoTratamento = codigoTratamento;
    }
    
    public static int getSTATUS_REGISTRADA() {
        return STATUS_REGISTRADA;
    }

    public static int getSTATUS_CANCELADA() {
        return STATUS_CANCELADA;
    }

    public static int getSTATUS_FINALIZADA() {
        return STATUS_FINALIZADA;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Boolean isExamePendente() {
        return examePendente;
    }

    public void setExamePendente(Boolean examePendente) {
        this.examePendente = examePendente;
    }

    public int getGravidade() {
        return gravidade;
    }

    public void setGravidade(int gravidade) {
        this.gravidade = gravidade;
    }
    
    
    
}
