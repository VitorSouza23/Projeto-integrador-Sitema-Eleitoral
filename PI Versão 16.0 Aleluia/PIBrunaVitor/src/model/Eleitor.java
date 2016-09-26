/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import excecaoEleitor.MatriculaException;
import excecaoEleitor.NomeException;
import model.exceptions.ValidarCursoException;
import model.exceptions.ValidarEmailException;

/**
 *
 * @author Bruna Branco
 */
public class Eleitor {
    private int id;
    private String nome;
    private String email;
    private String matricula;
    private String curso;
    private boolean selecionado;
    private Voto voto;

    public Eleitor() {
    }

    public Eleitor(int id, String nome, String email, String matricula, String curso, Voto voto) throws NomeException, MatriculaException, ValidarEmailException, ValidarCursoException {
        validarMatricula(matricula);
        validarNome(nome);
        validarEmail(email);
        validarCurso(curso);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.curso = curso;
        this.voto = voto;
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
    public void setNome(String nome) throws NomeException {
        validarNome(nome);
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) throws ValidarEmailException {
        validarEmail(email);
        this.email = email;
    }

    

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) throws MatriculaException {
        validarMatricula(matricula);
        this.matricula = matricula;
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) throws ValidarCursoException {
        validarCurso(curso);
        this.curso = curso;
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
     * @return the voto
     */
    public Voto getVoto() {
        return voto;
    }

    /**
     * @param voto the voto to set
     */
    public void setVoto(Voto voto) {
        this.voto = voto;
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
    
    private void validarNome(String nome) throws NomeException {
        if (nome.isEmpty() == true) {
            throw new NomeException("Não pode haver nome em branco!");
        }
        String caracterEspecial = "[a-zA-ZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄç\\-\\'\\s ]*";
        if (nome.matches(caracterEspecial) == false) {
            throw new NomeException("Caracteres inválidos (Ex: @#$%!&) detectados no nome!");
        }
    }

    private void validarMatricula(String matricula) throws MatriculaException {
        if (matricula.isEmpty()) {
            throw new MatriculaException("A não pode estar vazia");
        }else{
            if(matricula.length() != 11){
                throw new MatriculaException("A matrícula deve conter 11 caracteres!" +"\n"
                        + "(O traço (-) também é contado como caracter.)");
            }
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
    private void validarCurso(String curso) throws ValidarCursoException{
        if(curso.isEmpty()){
            throw new ValidarCursoException("O curso não pode estar em branco!");
        }
        
    }

}
