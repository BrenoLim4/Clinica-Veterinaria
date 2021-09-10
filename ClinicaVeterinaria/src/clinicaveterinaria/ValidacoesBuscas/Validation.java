
package clinicaveterinaria.ValidacoesBuscas;

import clinicaveterinaria.Entidades.Animal;
import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.Medico;
import clinicaveterinaria.EntidadesLogicas.Sessao;
import clinicaveterinaria.EntidadesLogicas.Tratamento;
import clinicaveterinaria.Repositorio.BancoDeDados;

/**
 *
 * @author BrenoLima
 */
public class Validation {

    private final BancoDeDados banco;
    private Cliente cliente;
    private Medico medico;
    private Animal animal;
    private Sessao sessao;
    private Tratamento tratamento;

    public Validation(BancoDeDados banco) {
        this.banco = banco;
    }

    public Validation(BancoDeDados banco, Cliente cliente, Medico medico, Animal animal) {
        this.banco = banco;
        this.cliente = cliente;
        this.medico = medico;
        this.animal = animal;
    }

    public Validation(BancoDeDados banco, Cliente cliente) {
        this.banco = banco;
        this.cliente = cliente;
    }

    public Validation(BancoDeDados banco, Animal animal, Cliente cliente) {
        this.banco = banco;
        this.animal = animal;
        this.cliente = cliente;
    }

    public Validation(BancoDeDados banco, Medico medico) {
        this.banco = banco;
        this.medico = medico;
    }

    //<editor-fold defaultstate="collapsed" desc="Validação das informações">
    public boolean verificarCliente() {
        if (!banco.getClientes().isEmpty()) {
            for (Cliente cliente : banco.getClientes()) {
                if (cliente.equals(this.cliente)) {
                    this.cliente = cliente;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarAnimal() {
        if (!banco.getAnimais().isEmpty()) {
            for (Animal animal : banco.getAnimais()) {
                if (animal.equals(this.animal)) {
                    this.animal = animal;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarMedico() {
        if (!banco.getMedicos().isEmpty()) {            
            for (Medico item : banco.getMedicos()) {
                if (item.equals(this.medico)) {
                    this.medico = item;
                    return true;
                }                
            }
        }
        return false;
    }

    public boolean isPrimeiraSessao(Sessao newSession) {
        if (!banco.getSessoes().isEmpty()) {
            for (Sessao sessao : banco.getSessoes()) {
                if (sessao.equals(newSession)) {
                    if (!banco.getTratamentos().isEmpty()) {                        
                        for (Tratamento tratamento : banco.getTratamentos()) {
                            if (tratamento.getSessoes().contains(sessao)) {
                                this.tratamento = tratamento;
                                return false;
                            }                            
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public void setTratamento(Tratamento tratamento){
        this.tratamento = tratamento;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }
    

}
