/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastros.configTabelaEleitor;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Eleitor;


public class EleitorPesoVotoTableModel extends AbstractTableModel {

    private List<Eleitor> lista;

    private boolean ordenarPorNome = true;

    public EleitorPesoVotoTableModel(List<Eleitor> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Eleitor ref = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ref.getNome();
            case 1:
                return ref.getMatricula();
            case 2:
                return ref.getEmail();
            case 3:
                return ref.getCurso();
            case 4:
                return ref.getVoto().getTipo();
        }
        return null;
    }

    public Eleitor getObjetoSelecionado(int rowIndex) {
        return lista.get(rowIndex);
    }

    public boolean isOrdenarPorNome() {
        return ordenarPorNome;
    }

    public void setOrdenarPorNome(boolean ordenarPorNome) {
        this.ordenarPorNome = ordenarPorNome;
    }

}
