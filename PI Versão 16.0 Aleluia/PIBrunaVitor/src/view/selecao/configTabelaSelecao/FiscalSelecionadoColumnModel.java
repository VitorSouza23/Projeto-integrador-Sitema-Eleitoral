/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.selecao.configTabelaSelecao;


import java.awt.FontMetrics;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;



    public class FiscalSelecionadoColumnModel extends DefaultTableColumnModel {

        private TableColumn criaColuna(int columnIndex, int largura,
                FontMetrics fm, boolean resizeable, String titulo) {
            int larguraTitulo = fm.stringWidth(titulo + " ");
            if (largura < larguraTitulo) {
                largura = larguraTitulo;
            }
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

        
        public FiscalSelecionadoColumnModel(FontMetrics fm) {
            int digito = fm.stringWidth("0");
            int letra = fm.stringWidth("M");
            addColumn(criaColuna(0, 40 * letra, fm, true, "Nome"));
            addColumn(criaColuna(1, 10 * letra, fm, true, "Telefone"));
            addColumn(criaColuna(2, 20 * letra, fm, true, "Email"));
            addColumn(criaColuna(3, 30 * letra, fm, true, "Endereco"));
            addColumn(criaColuna(4, 20 * letra, fm, true, "Selecionado"));
            
        }
    }
