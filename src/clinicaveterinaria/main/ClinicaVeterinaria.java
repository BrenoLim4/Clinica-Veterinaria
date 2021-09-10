/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicaveterinaria.main;

import clinicaveterinaria.Menus.MenusInterativos;
import clinicaveterinaria.Repositorio.BancoDeDados;
import java.util.Scanner;

/**
 *
 * @author Breno
 */
public class ClinicaVeterinaria {

    private static final int        USUARIO_SECRETARIA = 1;
    private static final int        USUARIO_MEDICO     = 2;
    private static final int        USUARIO_CLIENTE    = 3;
    public  static Scanner          sc = new Scanner(System.in);
    private static BancoDeDados     banco;
    private static int              tipoUsuario;
    private static MenusInterativos menu;

    public static void main(String[] args) {
        banco = getInstanceBancoDeDados();
        menu = new MenusInterativos(banco);
        do {
            System.out.println("Selecione o tipo de usuário"
                    + "\n[1] - Secretária"
                    + "\n[2] - Médico"
                    + "\n[3] - Cliente");
            setTipoUsuario(sc.nextInt());
            switch (tipoUsuario) {
                case USUARIO_SECRETARIA:
                    menu.menuSecretaria("Maria"); 
                    break;
                case USUARIO_MEDICO:
                    sc.nextLine();
                    menu.menuMedico();
                    break;
                case USUARIO_CLIENTE:
                    //em desenvolvimento
                    break;
            }
        } while (true);
    }

//<editor-fold defaultstate="collapsed" desc="Medoto setar tipo usuário">
    private static void setTipoUsuario(int tipoUsuario) {
        switch (tipoUsuario) {
            case USUARIO_SECRETARIA:
                ClinicaVeterinaria.tipoUsuario = USUARIO_SECRETARIA;
                break;
            case USUARIO_MEDICO:
                ClinicaVeterinaria.tipoUsuario = USUARIO_MEDICO;
                break;
            case USUARIO_CLIENTE:
                ClinicaVeterinaria.tipoUsuario = USUARIO_CLIENTE;
                break;
            default:
                System.out.println("Nenhum usuário correspondente");
        }
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Pegar Instância do Banco de Dados">    
    private static BancoDeDados getInstanceBancoDeDados() {
        AbrirBd bd = new AbrirBd();
        if (bd.verificar()) {
            return bd.getBd();
        } else {
            System.out.println("Acesso ao Banco de Dados Negado");
        }
        return bd.getBd();

    }
    //</editor-fold>   
}

//<editor-fold defaultstate="collapsed" desc="Conectar ao Banco">
class AbrirBd {
    
    BancoDeDados bd = new BancoDeDados();
    
    public AbrirBd() {
        
        bd.verificarChaveAcesso("f8cf8644");
        
    }
    
    public boolean verificar() {
        return bd.verificarChaveAcesso("f8cf8644");
        
    }
    
    public BancoDeDados getBd() {
        return bd;
    }
}
//</editor-fold>
