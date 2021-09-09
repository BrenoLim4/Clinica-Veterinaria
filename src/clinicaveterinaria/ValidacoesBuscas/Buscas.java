package clinicaveterinaria.ValidacoesBuscas;

import clinicaveterinaria.EntidadesLogicas.Exame;
import clinicaveterinaria.EntidadesLogicas.Sessao;
import clinicaveterinaria.EntidadesLogicas.Tratamento;
import clinicaveterinaria.Repositorio.BancoDeDados;

/**
 *
 * @author BrenoLima
 */
public class Buscas {

    private BancoDeDados banco;
    private Sessao sessao;
    private Tratamento tratamento;
    private Exame exame;
    private Integer index;

    public Buscas(BancoDeDados banco, Sessao sessao) {
        this.banco = banco;
        this.sessao = sessao;
    }

    public boolean buscarSessao() {
        if (!banco.getSessoes().isEmpty()) {

            for (Tratamento tratamento : banco.getTratamentos()) {
                for (Sessao session : tratamento.getSessoes()) {
                    if (session.getCliente().getCpf().equals(sessao.getCliente().getCpf())
                            && session.getPaciente().getNome().equalsIgnoreCase(sessao.getPaciente().getNome())
                            && sessao.getStatus() == Sessao.getSTATUS_REGISTRADA()) {
                        sessao = session;
                        index = banco.getTratamentos().indexOf(sessao);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Integer getIndexExame() {

        if (tratamento != null) {
            for (Exame item : tratamento.getExames()) {
                if (item.getEncerrada() == false) {
                    exame = item;
                    return tratamento.getExames().indexOf(item);
                }
            }
        }

        return index;
    }

    //<editor-fold defaultstate="collapsed" desc="Get's and Set">
    public BancoDeDados getBanco() {
        return banco;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setBanco(BancoDeDados banco) {
        this.banco = banco;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Integer getIndex() {
        return index;
    }

//</editor-fold> 
}
