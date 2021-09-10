package clinicaveterinaria.Beans;

import clinicaveterinaria.Entidades.Animal;
import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.Medico;
import clinicaveterinaria.EntidadesLogicas.Exame;
import clinicaveterinaria.EntidadesLogicas.Sessao;
import clinicaveterinaria.EntidadesLogicas.Tratamento;
import clinicaveterinaria.Repositorio.BancoDeDados;
import clinicaveterinaria.ValidacoesBuscas.Buscas;
import clinicaveterinaria.ValidacoesBuscas.Validation;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author BrenoLima
 */
public class GerenciarConsultaBean {

    private BancoDeDados banco;
    private Medico medico;
    private Sessao sessao;
    private Tratamento tratamento;
    private final Scanner sc = new Scanner(System.in);
    private Buscas busca;
    private final Validation validar;
    private Exame exame;

    public GerenciarConsultaBean(BancoDeDados banco, Medico medico) {
        this.banco = banco;
        this.medico = medico;
        validar = new Validation(banco);
    }

    public String gerenciarSessao() {
        sessao = new Sessao();
        sessao.setCliente(new Cliente());
        sessao.setPaciente(new Animal());
        int opcao;
        Integer index;
        System.out.println(medico.getNome() + ", digite os dados da sessão");
        System.out.println("Cliente"
                + "\nCpf: ");
        sessao.getCliente().setCpf(sc.nextLine());
        System.out.println("Paciente"
                + "\nNome: ");
        sessao.getPaciente().setNome(sc.nextLine());
        busca = new Buscas(banco, sessao);
        if (!busca.buscarSessao()) {
            return "Nenhuma sessão identificada";
        }
        //se chegar até aqui é pq pegou sessão 
        sessao = busca.getSessao();
        if(validar.isPrimeiraSessao(sessao)){
            return "Nenhuma sessão identificada";
        }//recupera tambem o tratamento
        sessao = validar.getSessao();
        Sessao sessaoOld = sessao;
        tratamento = validar.getTratamento();
        
        System.out.println("Sessão iniciada");
        System.out.println("Dados da sessão");
        System.out.println(sessao.toString());
        System.out.println("\n"
                + "Registrar Sintomas:");
        sessao.setSintomas(sc.nextLine());
        //VERIFICA SE A SESSÃO SOLICITA EXAME
        if (sessao.isExamePendente()) {
            busca.setTratamento(tratamento);
            System.out.println("Essa Sessão tem exames pendetes");
            index = busca.getIndexExame();
            if (index == null) {
                return "Não foi possível recuperar os dados do Exames";
            }
            exame = busca.getExame();
            exame.setDataHoraResultado(Calendar.getInstance().getTime());
            System.out.println("Informe o resultado do Exame");
            String resultado = sc.nextLine();
            exame.setResultado(resultado);
            exame.setEncerrada(Boolean.TRUE);
            tratamento.setSolicitaExame(Boolean.FALSE);
            try {
                tratamento.getExames().remove(index.intValue());
                tratamento.getExames().add(index, exame);
            } catch (NullPointerException np) {
                System.out.println("Não foi possivel salvar o exame");
                np.getMessage();
            }
            System.out.println("Resultado do exame registrado");
        }
        do {
            if (tratamento.isSolicitaExame() == true && sessao.getDiagnostico() != null) {
                break;
            }
            System.out.println("[1] -> Solicitar Exames"
                    + "\n[2] -> Diagnóstico"
                    + "\n[3] -> Sair");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    if (tratamento.isSolicitaExame() == true) {
                        System.out.println("Exame já foi solicitado");
                        break;
                    }
                    exame = new Exame(sessao.getPaciente(), sessao.getCliente(), sessao.getMedico());
                    tratamento.setSolicitaExame(Boolean.TRUE);
                    tratamento.getExames().add(exame);
                    System.out.println("Exame solicitado com sucesso");
                    break;
                case 2:
                    if(sessao.getDiagnostico() != null){
                        System.out.println("Diagnostico já foi dado");
                    }
                    System.out.println(medico.getNome() + ", informe seu diagnóstico.");
                    sessao.setDiagnostico(sc.nextLine());
                    System.out.println("Qual o gráu de grávidade?");
                    System.out.println("[1] -> Grávidade Baixa"
                            + "\n[2] -> Grávidade Média"
                            + "\n[3] -> Grávidade Alta");
                    sessao.setGravidade(sc.nextInt());
                    sc.nextLine();
                    if (sessao.getGravidade() == Sessao.getGRAVIDADE_BAIXA() && !sessao.isExamePendente()) {
                        if(!sessao.isExamePendente()){
                            System.out.println("outra sessão necessária, para trazer resultado do exame");
                            break;
                        }
                        tratamento.setConcluido(Boolean.TRUE);
                        System.out.println("Tratamento finalizado");
                    } else if (sessao.getGravidade() == Sessao.getGRAVIDADE_MEDIA() || sessao.getGravidade() == Sessao.getGRAVIDADE_ALTA()) {
                        tratamento.setConcluido(Boolean.FALSE);
                        System.out.println("Uma nova sessão terá que ser marcada");
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("opcão inválida");
            }
            } while (opcao != 3);
        
            sessao.setDataEncerramento(Calendar.getInstance().getTime());
            sessao.setStatus(Sessao.getSTATUS_FINALIZADA());
            // atualiza o objeto sessao em tratamento
            tratamento.getSessoes().remove(sessaoOld);
            tratamento.getSessoes().add(sessao);
            //atualiza s sessao e o tratameto no banco
            banco.salvarSessao(sessao);
            banco.salvarTratamento(tratamento);
            banco.getExames().add(exame);
            System.out.println("Tratamento e sessão atualizados com sucesso");
        

        return "Sessão finalizada com sucesso, até mais " + medico.getNome();
    }

    public BancoDeDados getBanco() {
        return banco;
    }

    public void setBanco(BancoDeDados banco) {
        this.banco = banco;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

}
