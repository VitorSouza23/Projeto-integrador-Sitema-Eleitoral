/*
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package view.selecao.configTabelaSelecao;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Candidato;
import model.exceptions.ValidacaoCandidatoException;


/**
 
 */
public class CandidatoSelecaoTableModel extends AbstractTableModel {

    private List<Candidato> lista;
    private boolean selecionado = false;
    private boolean ordenarPorNome = true;
   

    public CandidatoSelecaoTableModel(List<Candidato> lista) {
        this.lista = lista;
        
            
    }

    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Candidato object = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return object.getNome();
            case 1:
       
            return object.getNumero();
        
            case 2:
                return new ImageIcon(object.getFoto());
            case 3:
                return object.isSelecionado();

        }
        return null;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public Candidato getValoresCandidato(int rowIndex) {
        return lista.get(rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) {
            return Boolean.class;
        } else if(columnIndex == 2) {
            return ImageIcon.class;
        }else{
            return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 3;

    }
    
    @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if(columnIndex == 3){
                lista.get(rowIndex).setSelecionado((boolean) aValue);
                this.fireTableCellUpdated(rowIndex, columnIndex);
            }
            if(columnIndex == 2){
                try {
                    lista.get(rowIndex).setFoto((byte[]) aValue);
                } catch (ValidacaoCandidatoException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
                this.fireTableCellUpdated(rowIndex, columnIndex);
            }
        }

    
}
