package clinicaveterinaria.Entidades;

import java.util.Comparator;

/**
 *
 * @author BrenoLima
 */
public class PessoaComparator implements Comparator<Pessoa>{
    private static final int ORDENAR_POR_NOME  = 1;
    private static final int ORDENAR_POR_IDADE = 2;
    private static final int ORDENAR_POR_SEXO  = 3;
    private final int ordenarPor;
    
    public PessoaComparator(int ordenarPor){
        this.ordenarPor = ordenarPor;
    }
    
    @Override
    public int compare(Pessoa p1, Pessoa p2) {
        switch(ordenarPor){
            case ORDENAR_POR_NOME:  return p1.getNome().compareTo(p2.getNome());
            case ORDENAR_POR_IDADE: return p1.getIdade().compareTo(p2.getIdade());
            case ORDENAR_POR_SEXO:  return p1.getSexo().compareTo(p2.getSexo());            
            default: return 0;    
        }
    }
    
}
