/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.armazenamento.tabelaArmazenamentoConfig;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Fiscal;


public class FiscalTableModel extends AbstractTableModel {

    private List<Fiscal> lista;

    private boolean ordenarPorNome = true;

    public FiscalTableModel(List<Fiscal> lista) {
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

}
