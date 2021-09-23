
package clinicaveterinaria.EntidadesLogicas;

import clinicaveterinaria.Entidades.Animal;
import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.Medico;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author BrenoLima
 */
public class Tratamento {
    //criar códico do tratamento semelhante ao de sessão, seria: (banco.getTratamentos.size +1)
    //para não precisar setar novamente os atributos de cliente, animal e medico em sessão
    private Integer codigo;
    private List<Sessao> sessoes   = new ArrayList<>();
    private Boolean      concluido = false;
    private Cliente      cliente;
    private Animal       paciente;
    private Medico       medico;
    private List<Exame>  exames    = new ArrayList<>();    
    private Boolean      solicitaExame  = false;
    
    public Tratamento(Cliente cliente, Animal animal, Medico medico){
        this.cliente  = cliente;
        this.paciente = animal;
        this.medico   = medico;        
    }
    public Tratamento(Sessao sessao){
        sessoes.add(sessao);
        cliente  = sessao.getCliente();
        paciente = sessao.getPaciente();
        medico   = sessao.getMedico();
    }
    public Tratamento(){
        
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
        final Tratamento other = (Tratamento) obj;

        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return Objects.equals(this.medico, other.medico);
    }

    @Override    
    public String toString() {
        StringBuilder bui = new StringBuilder();
        for(Sessao sessao : sessoes){
            bui.append("Codigo: ").append(sessao.getCodigoSessao()).append("\n")
            .append("Sintomas: ").append(sessao.getSintomas()).append("\n")
            .append("Status: ").append(sessao.getStatus()).append("\n");
        }
        return "Tratamento{"
                    + "\nSessoes{\n" 
                    +       bui.toString()
                    + "\n}"
                    + "\nConcluido:"    + (concluido == true ? "Sim" : "Não")
                    + "\nCliente: "     + cliente.getNome()
                    + "\nPaciente: "    + paciente.getNome()
                    + "\nMedico: "      + medico.getNome()
                    + "\nQtd exames: "  + exames.size()
                + '}';
    }
//<editor-fold defaultstate="collapsed" desc="Get's and Set's">
    
    public List<Sessao> getSessoes() {
        return sessoes;
    }
    
    public void setSessoes(List<Sessao> sessoes) {
        this.sessoes = sessoes;
    }
    
    public Boolean isConcluido() {
        return concluido;
    }
    
    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Animal getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Animal paciente) {
        this.paciente = paciente;
    }
    
    public Medico getMedico() {
        return medico;
    }
    
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public List<Exame> getExames() {
        return exames;
    }
    
    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }
    
     public Boolean isSolicitaExame() {
        return solicitaExame;
    }

    public void setSolicitaExame(Boolean solicitaExame) {
        this.solicitaExame = solicitaExame;
    }
    
    public Integer getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
//</editor-fold>

    

    

   
    
    
}
