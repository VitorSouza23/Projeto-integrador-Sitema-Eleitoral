/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.exceptions.ValidarAdministradorException;

/**
 *
 * @author Cliente
 */
public class Administrador {
    private int idAdministrador;
    private String usuario;
    private String senha;
    

    public Administrador(int idAdministrador, String usuario, String senha) throws ValidarAdministradorException {
        validarAdministrador(usuario, senha);
        this.idAdministrador = idAdministrador;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Administrador() {
    }
    

    /**
     * @return the idAdministrador
     */
    public int getIdAdministrador() {
        return idAdministrador;
    }

    /**
     * @param idAdministrador the idAdministrador to set
     */
    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     * @throws model.exceptions.ValidarAdministradorException
     */
    public void setUsuario(String usuario) throws ValidarAdministradorException {
        validarUsuario(usuario);
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) throws ValidarAdministradorException {
        validarSenha(senha);
        this.senha = senha;
    }
    
    private void validarAdministrador(String usuario, String senha) throws ValidarAdministradorException{
        validarUsuario(usuario);
        validarSenha(senha);
    }

    private void validarUsuario(String usuario) throws ValidarAdministradorException {
        if(usuario.isEmpty()){
            throw new ValidarAdministradorException(0, "O nome do administrador não pode estar em branco!");
        }
    }

    private void validarSenha(String senha) throws ValidarAdministradorException {
        if(senha.length() > 8){
            throw new ValidarAdministradorException(1, "O tamanho da senha não pode ser maior que 8 caracteres!");
            
        }
        
        if(senha.length() < 5){
            throw new ValidarAdministradorException(2, "O tamnho da senha não pode ser menor que 5 caracteres!");
        }
    }
    
}
