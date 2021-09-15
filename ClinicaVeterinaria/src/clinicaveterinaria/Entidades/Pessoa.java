package clinicaveterinaria.Entidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author BrenoLim4
 */
public abstract class Pessoa {

    private static final int SEXO_MASCULINO = 1;
    private static final int SEXO_FEMININO = 2;
    private String nome = "";
    private String telefone = "";
    private String cpf = "";
    private Date dataNascimento;
    private Integer idade;
    private Integer sexo;

    public Pessoa() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.telefone);
        hash = 89 * hash + Objects.hashCode(this.idade);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.dataNascimento, other.dataNascimento)) {
            return false;
        }
        return Objects.equals(this.idade, other.idade);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return "\nNome: " + nome
                + "\nTelefone: " + telefone
                + "\nDataNascimento: " + format.format(dataNascimento)
                + "\nIdade: " + idade
                + "\nCpf: " + cpf
                + "\nSexo: " + getSexo();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        Date dataAtual = Calendar.getInstance().getTime();
        idade = (dataAtual.getYear() - dataNascimento.getYear());
        this.dataNascimento = dataNascimento;
    }

    public static int getSEXO_MASCULINO() {
        return SEXO_MASCULINO;
    }

    public static int getSEXO_FEMININO() {
        return SEXO_FEMININO;
    }

    public String getSexo() {
        if (sexo.equals(SEXO_MASCULINO)) {
            return "Masculino";
        } else {
            return "Feminino";
        }
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdade() {

        return idade;
    }

}
