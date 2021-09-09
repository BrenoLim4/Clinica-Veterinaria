/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author BrenoLim4
 */
public class Cliente extends Pessoa {
    private List<Animal> listaAnimal = new ArrayList<>();

    public Cliente(){
        
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.listaAnimal);

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
        final Cliente other = (Cliente) obj;
        if (!this.getNome().equalsIgnoreCase(other.getNome())){
            return false;
        }
        return this.getCpf().equals(other.getCpf());
    }

    @Override
    public String toString() {
        return "Cliente {" 
                + super.toString()
                + "\nAnimais{"
                + listaAnimal.toString()
                + "\n  }"
                + "\n}";
    }

    public List<Animal> getListaAnimal() {
        return listaAnimal;
    }

    public void setListaAnimal(List<Animal> listaAnimal) {
        this.listaAnimal = listaAnimal;
    }


    
    
}
