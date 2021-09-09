/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Entidades;

import clinicaveterinaria.EntidadesLogicas.Sessao;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author BrenoLim4
 */
public class Medico extends Pessoa {
    private String especialidade;
    private List<Sessao> consultas = new ArrayList<>();
    private Sessao consulta;
    private List<Animal> pacientes = new ArrayList<>();
    private Animal paciente;

    public Medico(){         
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.especialidade);
        hash = 11 * hash + Objects.hashCode(this.consultas);
        hash = 11 * hash + Objects.hashCode(this.pacientes);
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
        final Medico other = (Medico) obj;

        if (!this.getNome().equalsIgnoreCase(other.getNome())){
            return false;
        }        
        return Objects.equals(this.getCpf(), other.getCpf());
    }

    @Override
    public String toString() {
        return "Medico{" + "especialidade=" + especialidade + ", consultas=" + consultas + ", pacientes=" + pacientes + '}';
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<Animal> getPacientes() {
        return pacientes;
    }

    public List<Sessao> getConsultas() {
        return consultas;
    }

    public void setConsulta(Sessao consulta) {
        this.consulta = consulta;
        consultas.add(consulta);
    }

    public void setPaciente(Animal paciente) {
        this.paciente = paciente;
        pacientes.add(paciente);
    }
    
    
}
