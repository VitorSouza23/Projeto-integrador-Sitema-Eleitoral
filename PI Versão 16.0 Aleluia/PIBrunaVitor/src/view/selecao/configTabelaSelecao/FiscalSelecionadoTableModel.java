/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.selecao.configTabelaSelecao;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Fiscal;


public class FiscalSelecionadoTableModel extends AbstractTableModel {

    private List<Fiscal> lista;
    private boolean selecionado = false;
    private boolean ordenarPorNome = true;

    public FiscalSelecionadoTableModel(List<Fiscal> lista) {
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
        Fiscal ref = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ref.getNome();
            case 1:
                return ref.getTelefone();
            case 2:
                return ref.getEmail();
            case 3:
                return ref.getEndereco();
            case 4:
                return ref.isSelecionado();
        }
        return null;
    }

    public Fiscal getObjetoSelecionado(int rowIndex) {
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
