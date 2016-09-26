/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import conexao.exceptions.ConnectionException;
import conexao.exceptions.SQLStatementException;
import java.sql.SQLException;

import model.exceptions.ValidacaoCandidatoException;
import model.exceptions.ValidarEleicaoException;

/**
 *
 * @author Bruna Branco
 */
public class Candidato {

    private String nome;
    private byte[] foto;
    private int numero;
    private int idCandidato;
    private boolean selecionado = false;
    private double valorDeVotosRecebidos;

    public Candidato() {
    }

    public Candidato(String nome, byte[] foto, int numero, int idCandidato) throws ValidacaoCandidatoException, ConnectionException, SQLStatementException, SQLException {
        validarCandidato(nome, numero, foto);
        this.nome = nome;
        this.foto = foto;
        this.numero = numero;
        this.idCandidato = idCandidato;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     * @throws model.exceptions.ValidacaoCandidatoException
     */
    public void setNome(String nome) throws ValidacaoCandidatoException {
        this.validarNome(nome);
        this.nome = nome;
    }

    /**
     * @return the foto
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) throws ValidacaoCandidatoException  {
        validarImagem(foto);
        this.foto = foto;
    }

    /**
     * @return the numero
     */
    public int getNumero(){
        
        return numero;
    }

    /**
     * @param numero the numero to set
     * @throws conexao.exceptions.ConnectionException
     * @throws model.exceptions.ValidacaoCandidatoException
     * @throws conexao.exceptions.SQLStatementException
     * @throws java.sql.SQLException
     */
    public void setNumero(int numero) throws ConnectionException, ValidacaoCandidatoException, SQLStatementException, SQLException {
        this.validarNumero(numero);
        this.numero = numero;
    }

    /**
     * @return the idCandidato
     */
    public int getIdCandidato(){
        
        return idCandidato;
    }

    /**
     * @param idCandidato the idCandidato to set
     */
    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
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

    private void validarCandidato(String nome, int numero, byte[] foto) throws ValidacaoCandidatoException, ConnectionException, SQLStatementException, SQLException {
        this.validarNome(nome);
        this.validarNumero(numero);
        this.validarImagem(foto);
    }
    
    public void validarNome(String nome) throws ValidacaoCandidatoException{
        if (nome.isEmpty()) {
            throw new ValidacaoCandidatoException(0, "O campo nome não pode estar vazio!");
        }

        if (!nome.matches("[a-zA-ZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄç\\-\\'\\s ]*")) {
            throw new ValidacaoCandidatoException(1, "O nome não pode conter caracteres especiais, como: !@#$%% etc.");
        }
    }
    
    public void validarNumero(int numero) throws ConnectionException, ValidacaoCandidatoException, SQLStatementException, SQLException{
        
        
        if(numero <= 0){
            throw new ValidacaoCandidatoException(3, "Esse número não é válido para candidatos!" + "\n"
                    + "Por favor, selecione outro número.");
        }
        
        String tamanhoNumero = Integer.toString(numero);
        if(tamanhoNumero.length() > 4){
            throw new ValidacaoCandidatoException(2, "O número não pode ter mais de 4 caracteres!");
        }
    }
    
    public void validarImagem(byte[] imagem) throws ValidacaoCandidatoException{
        if(imagem == null){
            throw new ValidacaoCandidatoException(4, "Campo de imagem vazio! Por favor, escolha uma imagem para o Candidato!");
        }
    }

    /**
     * @return the valorDeVotosRecebidos
     */
    public double getValorDeVotosRecebidos() {
        return valorDeVotosRecebidos;
    }

    /**
     * @param valorDeVotosRecebidos the valorDeVotosRecebidos to set
     */
    public void setValorDeVotosRecebidos(double valorDeVotosRecebidos) {
        this.valorDeVotosRecebidos = valorDeVotosRecebidos;
    }
}
