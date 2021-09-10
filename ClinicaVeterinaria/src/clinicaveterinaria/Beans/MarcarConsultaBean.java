package clinicaveterinaria.Beans;

import clinicaveterinaria.ValidacoesBuscas.Validation;
import clinicaveterinaria.Entidades.Animal;
import clinicaveterinaria.Entidades.Cliente;
import clinicaveterinaria.Entidades.Medico;
import clinicaveterinaria.EntidadesLogicas.Sessao;
import clinicaveterinaria.EntidadesLogicas.Tratamento;
import clinicaveterinaria.Repositorio.BancoDeDados;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BrenoLima
 */
public class MarcarConsultaBean {

    private final int CLIENTE = 1;
    private final int PACIENTE = 2;
    private final int MEDICO = 3;
    private final Validation validar;
    private final Scanner sc = new Scanner(System.in);
    private final BancoDeDados banco;
    private Cliente cliente;
    private Animal animal;
    private Medico medico;
    private Tratamento tratamento;
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public MarcarConsultaBean(BancoDeDados banco) {
        this.banco = banco;
        validar = new Validation(banco);
    }

    public String marcarConsulta() {
        int opcao, verificar = verificarErroInformacoesPessoais(CLIENTE);

        if (verificar == CLIENTE || verificar == PACIENTE) {
            if (verificar == CLIENTE) {
                System.out.println("Deseja cadastrar o cliente? [1] - Sim / [2] - Não");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:
                        CadastroBean cadastro = new CadastroBean(banco, cliente);
                        cadastro.cadastrarCliente();
                        cliente = cadastro.getCliente();
//                        if(cadastro.getAnimal() == null){
//                            cadas
//                        }   
                        cadastro.setAnimal(animal);
                        cadastro.cadastrarAnimal();
                        animal = cadastro.getAnimal();
                        break;
                    case 2:
                        return "Consulta não finalizada";
                    default:
                        System.out.println("Opção inválida");
                        return "Consulta não finalizada";
                }

            } else {
                System.out.println("Deseja cadastrar o paciente? [1] - Sim / [2] - Não");
                opcao = sc.nextInt();
                switch (opcao) {
                    case 1:
                        CadastroBean cadastro = new CadastroBean(banco, animal);
                        cadastro.setCliente(cliente);
                        cadastro.cadastrarAnimal();
                        animal = cadastro.getAnimal();
                        break;
                    case 2:
                        return "Consulta não finalizada";
                    default:
                        System.out.println("Opção inválida");
                        return "Consulta não finalizada";
                }
            }
        }
        if (verificarErroInformacoesPessoais(MEDICO) == MEDICO) {
            System.out.println("Deseja cadastrar o médico? [1] - Sim / [2] - Não");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    CadastroBean cadastro = new CadastroBean(banco, medico);
                    cadastro.cadastrarMedico();
                    medico = cadastro.getMedico();
                    break;
                case 2:
                    return "Consulta não finalizada";
                default:
                    System.out.println("Opção inválida");
                    return "Consulta não finalizada";
            }
        }
        Sessao sessao = new Sessao(cliente, animal, medico);
        medico.setConsulta(sessao);
        medico.setPaciente(animal);
        validar.setTratamento(tratamento);
        String retorno;
        String dataAgendada;
        if (validar.isPrimeiraSessao(sessao)) {
            tratamento = new Tratamento(sessao);
            banco.getTratamentos().add(tratamento);
            System.out.println("Novo tratamento iniciado");
            do{
            System.out.println("Digite a data do Agendamento: formato[DD/MM/AAAA]");           
            dataAgendada = sc.nextLine();

            try {
                if(format.parse(dataAgendada).before(Date.from(Instant.now()))){
                    System.out.println("Data da consulta, não pode ser anterior a data atual.");
                }else{
                    sessao.setDataMarcada(format.parse(dataAgendada));
                }
            } catch (ParseException ex) {
                Logger.getLogger(MarcarConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            } while(sessao.getDataMarcada() != null);

            sessao.setStatus(Sessao.getSTATUS_REGISTRADA()); 
            banco.getSessoes().add(sessao);
            retorno = "Primeira sessão marcada com sucesso " + (sessao.getDataMarcada() == null ? "Data não foi informadada" : "para o dia: "+ sessao.getDataMarcada().toString());
        } else {
            tratamento = validar.getTratamento();

            if(validar.seassaoAnteriorConcluida(tratamento) == false){
                return "Não foi possível marcar uma nova consulta, anterior ainda está pendente.";
            }    
            System.out.println("Digite a data do Agendamento: formato[DD/MM/AAAA]");
            dataAgendada = sc.nextLine();

            try {
                sessao.setDataMarcada(format.parse(dataAgendada));
            } catch (ParseException ex) {
                Logger.getLogger(MarcarConsultaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(tratamento.isSolicitaExame() == true){
                sessao.setExamePendente(Boolean.TRUE);
            }
            banco.getSessoes().add(sessao);
            tratamento.getSessoes().add(sessao);
            retorno = String.valueOf(tratamento.getSessoes().size()) + "ª sessão, marcada com sucesso, dia " + sessao.getDataMarcada().toString();
        }
        return retorno;
    }

//<editor-fold defaultstate="collapsed" desc="metodo para verifica Erro das informações">
    public int verificarErroInformacoesPessoais(int tipoVerificao) {
        try{
        if (tipoVerificao == CLIENTE) {
            cliente = new Cliente();
            animal = new Animal();
            validar.setCliente(cliente);
            validar.setAnimal(animal);
            System.out.println("Preencha os dados do cliete e de seu animal");
            System.out.println("Dados do cliente"
                    + "\nNome:");
            cliente.setNome(sc.nextLine());
            System.out.println("Cpf: (Somente numeros)");
            cliente.setCpf(sc.nextLine());
            if (!validar.verificarCliente()) {
                System.out.println("Cliente não está cadastrado no Sistema");
                return CLIENTE;
            }
            System.out.println("Cliente encontrado");
            System.out.println("Dados do Animal");
            System.out.println("Nome: ");
            animal.setNome(sc.nextLine());
            System.out.println("Raça: ");
            animal.setRaca(sc.nextLine());
            System.out.println("Sexo: "
                    + "\n[1] - macho"
                    + "\n[2] - Fêmea");
            animal.setSexo(sc.nextInt());
            sc.nextLine();
            if (!validar.verificarAnimal()) {
                System.out.println("Animal não cadastrado no Sistema");
                return PACIENTE;
            }
            cliente = validar.getCliente();
            animal  = validar.getAnimal();
        } else if (tipoVerificao == MEDICO) {
            medico = new Medico();
            validar.setMedico(medico);            
            System.out.println("Informe os dados do médico, que realizará à consulta");
            System.out.println("Nome: ");
            medico.setNome(sc.nextLine());
            System.out.println("cpf: (Somente números)");
            medico.setCpf(sc.nextLine());
            if (!validar.verificarMedico()) {
                System.out.println("Nenhum Médico encontrado");
                return MEDICO;
            }
            medico = validar.getMedico();
        }
        } catch(NullPointerException np){
            np.printStackTrace();
        }

        return 0;
    }
//</editor-fold>

}