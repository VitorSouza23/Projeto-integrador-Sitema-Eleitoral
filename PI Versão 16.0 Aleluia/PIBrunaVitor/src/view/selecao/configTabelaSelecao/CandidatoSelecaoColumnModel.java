/*
 
 */

package view.selecao.configTabelaSelecao;

import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;


public class CandidatoSelecaoColumnModel extends DefaultTableColumnModel{
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
    
    public CandidatoSelecaoColumnModel(FontMetrics fm) {
        int digito = fm.stringWidth("0");
        int letra = fm.stringWidth("M");
        addColumn(criaColuna(0, 20 * letra, fm, true, "Nome"));
        addColumn(criaColuna(1, 12 * digito, fm, true, "Número"));
        addColumn(criaColuna(2, 20 * letra, fm, true, "Foto"));
        addColumn(criaColuna(3, 20 * letra, fm, true, "Selecionado"));
        
    } 
}
