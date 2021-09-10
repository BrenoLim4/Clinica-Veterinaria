/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Repositorio;

import clinicaveterinaria.Entidades.Animal;
import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.Medico;
import clinicaveterinaria.Entidades.Secretaria;
import clinicaveterinaria.EntidadesLogicas.Exame;
import clinicaveterinaria.EntidadesLogicas.Sessao;
import clinicaveterinaria.EntidadesLogicas.Tratamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author BrenoLima
 */
public class BancoDeDados {
    private List<Cliente>       clientes    = new ArrayList<>();
    private List<Animal>        animais     = new ArrayList<>();
    private List<Medico>        medicos     = new ArrayList<>();
    private List<Sessao>        sessoes     = new ArrayList<>();
    private List<Tratamento>    tratamentos = new ArrayList<>();
    private List<Exame>         exames      = new ArrayList<>();
    private List<Secretaria>    secretaria  = new ArrayList<>();
    private static final String AcessKey    = "f8cf8644";

    public BancoDeDados() {        
    }    
    
    public boolean verificarChaveAcesso(String AcessKey){
        return BancoDeDados.AcessKey.equals(AcessKey);
    }
    
    public void salvarTratamento(Tratamento tratamento){
        if(!tratamentos.isEmpty()){
            int index = 0;
            for(Tratamento item : tratamentos){
                if(item.equals(tratamento)){
                    index = tratamentos.indexOf(item);
                    tratamentos.remove(item);
                    tratamentos.add(index, tratamento);
                }
            }
        }
    }
    
    public void salvarSessao(Sessao sessao){
        if(!sessoes.isEmpty()){
            for(Sessao item : sessoes){
                if(item.equals(sessao)){
                    int index = sessoes.indexOf(item);
                    sessoes.remove(item);
                    sessoes.add(index, sessao);
                }
            }
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
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
        final BancoDeDados other = (BancoDeDados) obj;
        if (!Objects.equals(this.clientes, other.clientes)) {
            return false;
        }
        if (!Objects.equals(this.animais, other.animais)) {
            return false;
        }
        if (!Objects.equals(this.medicos, other.medicos)) {
            return false;
        }
        if (!Objects.equals(this.sessoes, other.sessoes)) {
            return false;
        }
        if (!Objects.equals(this.tratamentos, other.tratamentos)) {
            return false;
        }
        if (!Objects.equals(this.exames, other.exames)) {
            return false;
        }
        return Objects.equals(this.secretaria, other.secretaria);
    }

    //<editor-fold defaultstate="collapsed" desc="Get's">
    
    public static String getAcessKey() {
        return AcessKey;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public List<Animal> getAnimais() {
        return animais;
    }
    
    public List<Medico> getMedicos() {
        return medicos;
    }
    
    public List<Sessao> getSessoes() {
        return sessoes;
    }
    
    public List<Tratamento> getTratamentos() {
        return tratamentos;
    }
    
    public List<Exame> getExames() {
        return exames;
    }
    
    public List<Secretaria> getSecretaria() {
        return secretaria;
    }
    
//</editor-fold>
    
    
}
