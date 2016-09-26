/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.selecao.configTabelaSelecao;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Eleitor;


public class EleitorSelecionarTableModel extends AbstractTableModel {

    private List<Eleitor> lista;

    private boolean selecionado = false;
    private boolean ordenarPorNome = true;

    public EleitorSelecionarTableModel(List<Eleitor> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
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
                return ref.isSelecionado();
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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 4;

    }
    
    @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if(columnIndex == 4){
                lista.get(rowIndex).setSelecionado((boolean) aValue);
                this.fireTableCellUpdated(rowIndex, columnIndex);
            }
        }
}
