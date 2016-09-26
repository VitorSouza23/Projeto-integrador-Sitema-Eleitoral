/*
 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package view.armazenamento.tabelaArmazenamentoConfig;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

/**
 *
 * 
 */
public class CandidatoColumnModel extends DefaultTableColumnModel{
    private TableColumn criaColuna(int columnIndex, int largura,
            FontMetrics fm, boolean resizeable, String titulo) {
        int larguraTitulo = fm.stringWidth(titulo + " ");
        if (largura < larguraTitulo)
            largura = larguraTitulo;
        TableColumn col = new TableColumn(columnIndex);
        col.setHeaderRenderer(null);
        col.setHeaderValue(titulo);
        col.setPreferredWidth(largura);
        if (!resizeable) {
            col.setMaxWidth(largura);
            col.setMinWidth(largura);
        }
        col.setResizable(resizeable);
        return col;
    }
   
    public CandidatoColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criaColuna(0, 20 * letra, fm, true, "Nome"));
        addColumn(criaColuna(1, 12 * digito, fm, true, "Número"));
        addColumn(criaColuna(2, 20 * letra, fm, true, "Foto"));
        
        
    } 
}
