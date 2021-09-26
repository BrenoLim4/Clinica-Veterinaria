/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackBeans;

import clinicaveterinaria.ConnectBd.Conexao;
import clinicaveterinaria.Entidades.Pessoa;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author Breno
 */
@KeepAlive
@ManagedBean(name="login")
public class LoginBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private final String usuario = "";
    private final String senha = "";
    private Pessoa user;
    private StringBuilder sql = new StringBuilder();
    
    public LoginBean() {
        
    }

    public void validarLogin(){
        if(!(usuario.isEmpty() || senha.isEmpty())){
            try{
                
                Conexao conexao = new Conexao();
                Connection conn = conexao.getConexao();
                sql.append("SELECT * "
                        + "FROM clinica_veterinaria.users us"
                        + "WHERE us.matricula = '").append(usuario).append('´')
                        .append("AND us.senha = '").append(senha).append('´');
                        
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql.toString());
                
                
            }catch(SQLException ex){
                
            }
        }
    }
    
    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public Pessoa getUser() {
        return user;
    }
    
    
}
