package clinicaveterinaria.ConnectBd;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 99039833
 */
public class Conexao {

    public Connection getConexao() {
        String url = "jdbc:mysql://127.0.0.1:3306/clinica_veterinaria";
        //String url ="jdbc:mysql://localhost:3306/clinica_veterinaria";home-Ofice
        String user = "root";
        String password = "";
        try {             
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println("Não foi possivel encerrar conexão");
            ex.printStackTrace();}

        return null;
    }
    
    public void closeConexao(Connection conexao){
        if(conexao != null){
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Não foi possivel encerrar conexão");
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
