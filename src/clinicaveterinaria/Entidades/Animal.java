/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Entidades;

import java.util.Objects;

/**
 *
 * @author BrenoLim4
 */
public class Animal {

    private Cliente cuidador;
    private String nome;
    private String raca;
    private String especie;
    private Integer idade;
    private Integer sexo;
    private static final int MACHO = 1;
    private static final int FEMEA = 2;

    public Animal(Cliente cuidador) {
        this.cuidador = cuidador;
    }
    public Animal(){
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nome);
        hash = 41 * hash + Objects.hashCode(this.raca);
        hash = 41 * hash + this.idade;
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
        final Animal other = (Animal) obj;
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.raca, other.raca);
    }

    @Override
    public String toString() {
        return "Animal{"
                + "\nnome: " + nome
                + "\nraca: " + raca
                + "\nidade: " + idade
                + "\nsexo: " + (sexo == MACHO ? "Macho" : "Fêmia") + '}'
                + cuidador.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public static int getMACHO() {
        return MACHO;
    }

    public static int getFEMEA() {
        return FEMEA;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Cliente getCuidador() {
        return cuidador;
    }

    public void setCuidador(Cliente cuidador) {
        this.cuidador = cuidador;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

}
