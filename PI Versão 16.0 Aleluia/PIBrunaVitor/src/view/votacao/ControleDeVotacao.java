/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.votacao;

import model.Candidato;
import model.Eleicao;
import model.Eleitor;

/**
 *
 * @author Aluno
 */
public class ControleDeVotacao {
    private static Eleicao eleicaoSelecionada;
    private static Eleitor eleitorIdentificado;
    private static Candidato candidatoEscolhido;
    
   

    /**
     * @return the eleicaoSelecionada
     */
    public static Eleicao getEleicaoSelecionada() {
        return eleicaoSelecionada;
    }

    /**
     * @param aEleicao the eleicaoSelecionada to set
     */
    public static void setEleicaoSelecionada(Eleicao aEleicao) {
        eleicaoSelecionada = aEleicao;
    }

    /**
     * @return the eleitorIdentificado
     */
    public static Eleitor getEleitorIdentificado() {
        return eleitorIdentificado;
    }

    /**
     * @param aEleitorIdentificado the eleitorIdentificado to set
     */
    public static void setEleitorIdentificado(Eleitor aEleitorIdentificado) {
        eleitorIdentificado = aEleitorIdentificado;
    }

    /**
     * @return the candidatoEscolhido
     */
    public static Candidato getCandidatoEscolhido() {
        return candidatoEscolhido;
    }

    /**
     * @param aCandidatoEscolhido the candidatoEscolhido to set
     */
    public static void setCandidatoEscolhido(Candidato aCandidatoEscolhido) {
        candidatoEscolhido = aCandidatoEscolhido;
    }
    
    public static void limparDados(){
        setCandidatoEscolhido(null);
        setEleicaoSelecionada(null);
        setEleitorIdentificado(null);
    }
}
