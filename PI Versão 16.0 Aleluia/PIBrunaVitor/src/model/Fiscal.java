/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import excecaoEleitor.NomeException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Bruna Branco
 */
public class Fiscal {
    private int id;
    private String nome;
    private String endereco;
    private String email;
    private String telefone;
    private boolean selecionado;

    public Fiscal() {
    }

    public Fiscal(int id, String nome, String endereco, String email, String telefone) throws NomeException, ValidarEmailException {
        validarNome(nome);
        validarEmail(email);
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     * @throws excecaoEleitor.NomeException
     */
    public void setNome(String nome) throws NomeException {
        validarNome(nome);
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     * @throws model.exceptions.ValidarEmailException
     */
    public void setEmail(String email) throws ValidarEmailException {
        validarEmail(email);
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
   private void validarNome(String nome) throws NomeException{
        if(nome.isEmpty()){
            throw new NomeException("Não pode haver nome em branco!");
        }
        String caracterEspecial = "[a-zA-ZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄç\\-\\'\\s ]*";
        if(nome.matches(caracterEspecial) == false){
            throw new NomeException("Caracteres inválidos (Ex: @#$%!&) detectados no nome!");
        }
    }
    private void validarEmail(String email) throws ValidarEmailException{
        int y= email.length();
        int x= email.indexOf("@");
        int z= email.lastIndexOf(".");
        
        if(x<1||z<x+2||z+2>=email.length()){
            throw new ValidarEmailException("O email não é válido, tente um novo! Ex: pereira@gmail.com");
        }
        
    }

    /**
     * @return the selecionado
     */
    public boolean isSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
