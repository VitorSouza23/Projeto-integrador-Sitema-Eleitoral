/*
 * CreditCardTableModel.java
 *
 * 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package view.gerenciamento.tabelaConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Eleicao;

/**
 *
 * 

 
 */
public class EleicaoTableModel extends AbstractTableModel {

    private List<Eleicao> lista;
    private boolean selecionado = false;
    private boolean ordenarPorNome = true;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy '-' hh:mm:ss aaa");

    public EleicaoTableModel(List<Eleicao> lista) {
        this.lista = lista;
        
            
    }

    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Eleicao object = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return object.getNome();
            case 1:
                return sdf.format((object.getDataHoraInico()));
                
            case 2:
                return sdf.format((object.getDataHoraFinal()));
                
            case 3:
                if(object.isSituacao()){
                    return "Em andamento";
                }else{
                    return "Fechada";
                }
           
                

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

    public Eleicao getValoresEleicao(int rowIndex) {
        return lista.get(rowIndex);
    }

    
       

    
}
