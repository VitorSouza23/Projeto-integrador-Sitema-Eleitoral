/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.exceptions.ValidarEleicaoException;

/**
 *
 * @author Bruna Branco
 */
public class Eleicao {
    private int id;
    private String nome;
    private String tipoEleicao;
    private long dataHoraInico;
    private long dataHoraFinal;
    private boolean situacao;
    private ArrayList<Eleitor> listaEleitor = new ArrayList<>();
    private ArrayList<Candidato> listaCandidato = new ArrayList<>();
    private ArrayList<Fiscal> listaFiscal = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy '-' hh:mm:ss aaa");

    public Eleicao() {
    }

    public Eleicao(int id, String nome, String tipoEleicao, long dataHoraInico, long dataHoraFinal, boolean situacao) throws ValidarEleicaoException {
        validarEleicao(nome, tipoEleicao, dataHoraInico, dataHoraFinal);
        this.id = id;
        this.nome = nome;
        this.tipoEleicao = tipoEleicao;
        this.dataHoraInico = dataHoraInico;
        this.dataHoraFinal = dataHoraFinal;
        this.situacao = situacao;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) throws ValidarEleicaoException {
        validarNome(nome);
        this.nome = nome;
    }

    /**
     * @return the dataHoraInico
     */
    public long getDataHoraInico() {
        return dataHoraInico;
    }

    /**
     * @param dataHoraInico the dataHoraInico to set
     */
    public void setDataHoraInico(long dataHoraInico) throws ValidarEleicaoException {
        validarDataInicial(new Date(dataHoraInico));
        sdf.format(dataHoraInico);
        this.dataHoraInico = dataHoraInico;
    }

    /**
     * @return the dataHoraFinal
     */
    public long getDataHoraFinal() {
        return dataHoraFinal;
    }

    /**
     * @param dataHoraFinal the dataHoraFinal to set
     */
    public void setDataHoraFinal(long dataHoraFinal) throws ValidarEleicaoException {
        validarDataFinal(new Date(dataHoraFinal));
        sdf.format(dataHoraFinal);
        this.dataHoraFinal = dataHoraFinal;
    }

    /**
     * @return the listaEleitor
     */
    public ArrayList<Eleitor> getListaEleitor() {
        return listaEleitor;
    }

    /**
     * @param listaEleitor the listaEleitor to set
     */
    public void setListaEleitor(ArrayList<Eleitor> listaEleitor) {
        this.listaEleitor = listaEleitor;
    }

    /**
     * @return the listaCandidato
     */
    public ArrayList<Candidato> getListaCandidato() {
        return listaCandidato;
    }

    /**
     * @param listaCandidato the listaCandidato to set
     */
    public void setListaCandidato(ArrayList<Candidato> listaCandidato) {
        this.listaCandidato = listaCandidato;
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

    /**
     * @return the listaFiscal
     */
    public ArrayList<Fiscal> getListaFiscal() {
        return listaFiscal;
    }

    /**
     * @param listaFiscal the listaFiscal to set
     */
    public void setListaFiscal(ArrayList<Fiscal> listaFiscal) {
        this.listaFiscal = listaFiscal;
    }

    /**
     * @return the tipoEleicao
     */
    public String getTipoEleicao() {
        return tipoEleicao;
    }

    /**
     * @param tipoEleicao the tipoEleicao to set
     */
    public void setTipoEleicao(String tipoEleicao) throws ValidarEleicaoException {
        validarTipoEleicao(tipoEleicao);
        this.tipoEleicao = tipoEleicao;
    }

    /**
     * @return the situacao
     */
    public boolean isSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

   private void validarEleicao(String nome, String tipoEleicao, long horaInicial, long horaFinal) throws ValidarEleicaoException{
       Date dataInicial = new Date(horaInicial);
       Date dataFinal = new Date(horaFinal);
       validarNome(nome);
       validarTipoEleicao(tipoEleicao);
       validarData( dataInicial , dataFinal);
       validarDataInicial(dataInicial);
       validarDataFinal(dataFinal);
   }

    private void validarNome(String nome) throws ValidarEleicaoException {
        if(!nome.matches("[a-zA-ZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄç0-9\\-\\'\\s ]*")){
            throw new ValidarEleicaoException(0, "Não é possível utilizar caracteres especiais no nome da eleição! Ex: @#$%¨& etc.");
        }
        
        if(nome.isEmpty()){
            throw new ValidarEleicaoException(1, "O nome não pode estar em branco!");
        }
    }

    private void validarTipoEleicao(String tipoEleicao) throws ValidarEleicaoException {
        if(tipoEleicao.isEmpty()){
            throw new ValidarEleicaoException(5, "O tipo de Eleição não foi definido!");
        }
    }

    private void validarData(Date horaInicial, Date horaFinal) throws ValidarEleicaoException {
       
        
        if(horaInicial.after(horaFinal)){
            throw  new ValidarEleicaoException(4, "A DATA INICIAL não pode ser maior que a DATA FINAL!");
        }
        
    }

    private void validarDataInicial(Date data) throws ValidarEleicaoException {
        if(data ==  null){
            throw  new ValidarEleicaoException(2, "A data inicial não pode estar em branco!");
        }
    }
    
     private void validarDataFinal(Date data) throws ValidarEleicaoException {
         if(data ==  null){
            throw  new ValidarEleicaoException(3, "A data final não pode estar em branco!");
        }
    }
     
   
}
