/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Aluno
 */
public class Voto {
    private String tipo;
    private double valor;
    private int idVoto;

    public Voto() {
    }

    public Voto(String tipo, double valor, int idVoto) {
        this.tipo = tipo;
        this.valor = valor;
        this.idVoto = idVoto;
    }

    
    
    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void definirValorVoto(String tipo){
        switch (tipo) {
            case "Universal":
                this.valor = 1;
                break;
            case "50%":
                this.valor = 0.5;
                break;
            case "25%":
                this.valor = 0.25;
                break;
        }
    }

    /**
     * @return the idVoto
     */
    public int getIdVoto() {
        return idVoto;
    }

    /**
     * @param idVoto the idVoto to set
     */
    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }
}
