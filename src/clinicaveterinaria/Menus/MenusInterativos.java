/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.Menus;

import clinicaveterinaria.Beans.CadastroBean;
import clinicaveterinaria.Beans.GerenciarConsultaBean;
import clinicaveterinaria.Beans.MarcarConsultaBean;
import clinicaveterinaria.Entidades.Medico;
import clinicaveterinaria.Repositorio.BancoDeDados;
import clinicaveterinaria.ValidacoesBuscas.Validation;
import java.util.Scanner;

/**
 *
 * @author BrenoLima
 */
public class MenusInterativos {

    private Scanner sc = new Scanner(System.in);
    private int buttom;
    //<editor-fold defaultstate="collapsed" desc="Funções da Secretaria">
    private static final int MARCAR_CONSULTA    = 1;
    private static final int CADASTRAR_CLIENTE  = 2;
    private static final int CADASTRAR_ANIMAL   = 3;
    private static final int CADASTRAR_MEDICO   = 4;
    private static final int ATUALIZAR_CADASTRO = 5;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Funções do medico">
    private static final int GERENCIAR_CONSULTA = 1;
    private static final int CONSULTAR_SESSOES_FINALIZADAS = 2;
    private static final int CONSULTAR_PACIENTES = 3;
    private static final int CONSULTAR_TRATAMENTOS = 4;
    //</editor-fold>
    private static final int SAIR = 6;
    private static BancoDeDados banco;
    private Medico medico;

    public MenusInterativos(BancoDeDados banco) {
        MenusInterativos.banco = banco;
    }

    @SuppressWarnings("null")
    public void menuSecretaria(String nome) {
        System.out.printf("Bem vindo(a) [%s]\n", nome);
        do {
            System.out.println("Menu"
                    + "\n[1] - marcar consulta"
                    + "\n[2] - Cadastrar cliente"
                    + "\n[3] - Cadastrar Animal"
                    + "\n[4] - Cadastrar Médico"
                    + "\n[5] - Atualizar Cadastro"
                    + "\n[6] - Sair");
             buttom = sc.nextInt();
            CadastroBean cadastro = null;
            if (buttom >= CADASTRAR_CLIENTE && buttom <= CADASTRAR_MEDICO) {
                cadastro = new CadastroBean(banco);
            }
            switch (buttom) {
                case MARCAR_CONSULTA:
                    MarcarConsultaBean consulta = new MarcarConsultaBean(banco);
                    System.out.println(consulta.marcarConsulta());
                    break;
                case CADASTRAR_CLIENTE:
                    cadastro.cadastrarCliente();
                    break;
                case CADASTRAR_ANIMAL:
                    cadastro.cadastrarAnimal();
                    break;
                case CADASTRAR_MEDICO:
                    cadastro.cadastrarMedico();
                    break;
                case ATUALIZAR_CADASTRO:
                    //em desenvolvimento
                    break;
                case SAIR:
                    sc.nextLine();
                    break;
                default:
                    System.out.println("Opcao inválida");
            }
        } while (buttom != SAIR);
    }

    public void menuMedico() {
        medico = new Medico();
        Validation validar = new Validation(banco, medico);
        System.out.println("-> Fazer Login <-");
        System.out.println("Nome: ");
        medico.setNome(sc.nextLine());
        System.out.println("Cpf: (Somente números)");
        medico.setCpf(sc.nextLine());

        if (!validar.verificarMedico()) {
            System.out.println("Nenhum Médico encontrado");
            return;
        }
        medico = validar.getMedico();
        System.out.println("Bem vindo(a) " + medico.getNome());
        do{
            System.out.println("Menu"
                    + "\n[1] -> Gerênciar Consulta"
                    + "\n[2] -> Consultar Sessões finalizadas"
                    + "\n[3] -> Consultar Lista de Pacientes"
                    + "\n[4] -> Consultar tratamentos"
                    + "\n[6] -> Sair");
            
            buttom = sc.nextInt();

            switch(buttom){
                case GERENCIAR_CONSULTA:
                    GerenciarConsultaBean gerenciar = new GerenciarConsultaBean(banco, medico);
                    System.out.println(gerenciar.gerenciarSessao());
                    break;
                case CONSULTAR_SESSOES_FINALIZADAS:
                    //
                    break;
                case CONSULTAR_PACIENTES:
                    //
                case CONSULTAR_TRATAMENTOS:
                    //
                case SAIR:
                    System.out.println("saindo.....");
                    break;
                default:
                    System.out.println("Opção inválida");
                
            }
        }while(buttom != 6);

    }
}
