
package clinicaveterinaria.Beans;

import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.PessoaComparator;
import clinicaveterinaria.Repositorio.BancoDeDados;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 *
 * @author BrenoLima
 */
public class ConsultasBean {
    private final BancoDeDados banco;
    private final Scanner sc = new Scanner(System.in);
    private int ordenarPor;
    
    public ConsultasBean(BancoDeDados banco){
        this.banco = banco;
    }
    
    public void consultarListaClientes(){
        setTipoOrdenamento();
        Collections.sort(banco.getClientes(), new PessoaComparator(ordenarPor));
        banco.getClientes().forEach((Cliente cliente) -> {
            System.out.println(cliente.toString());
        });
    }
    
    public void consultarListaMedico(){
        setTipoOrdenamento();
        Collections.sort(banco.getMedicos(), new PessoaComparator(ordenarPor));
        banco.getMedicos().forEach((medico) -> { 
            System.out.println(medico);
        });
    }
    
    public void setTipoOrdenamento(){
        System.out.println("Ordenar por"
                + "\n[1] - Nome"
                + "\n[2] - Idade"
                + "\n[3] - Sexo");
        ordenarPor = sc.nextInt();
    }
}

