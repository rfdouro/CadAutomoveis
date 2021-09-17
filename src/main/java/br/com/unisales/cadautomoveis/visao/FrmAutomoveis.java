package br.com.unisales.cadautomoveis.visao;

import br.com.unisales.cadautomoveis.modelo.Automovel;
import br.com.unisales.cadautomoveis.modelo.Pessoa;
import br.com.unisales.cadautomoveis.persistencia.BancoDados;
import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.dizitart.no2.FindOptions;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.SortOrder;
import org.dizitart.no2.objects.filters.ObjectFilters;

public class FrmAutomoveis extends javax.swing.JDialog {

 public Automovel automovel = new Automovel();
 public List<Automovel> listaAutomoveis = new LinkedList<>();

 public void preencheComboProprietario() {
  List<Pessoa> pessoas = BancoDados.tabelaPessoas.find(
          FindOptions.sort("nome", SortOrder.Ascending)
  ).toList();
  DefaultComboBoxModel dcm = (DefaultComboBoxModel) cbProprietario.getModel();
  //remove todos os elementos antes de inserir
  dcm.removeAllElements();
  //adiciona as pessoas no combobox
  for(Pessoa p: pessoas){
   dcm.addElement(p);
  }
 }

 public Automovel selecionaPorPlaca(String placaPesq) {
  List<Automovel> lista = BancoDados.tabelaAutomoveis.find(
          ObjectFilters.eq("placa", placaPesq)
  ).toList();
  if (lista != null && lista.size() > 0) {
   return lista.get(0);
  }
  return null;
 }

 public void limpaCampos() {
  edtAno.setText("");
  edtModelo.setText("");
  edtPlaca.setText("");
  edtPlaca.requestFocus();
 }

 public void filtraDadosAutomoveis(String placaOuModelo) {
  preencheComboProprietario();
  String campoOrdem = "ano";
  switch (cbOrdem.getSelectedIndex()) {
   case 0:
    campoOrdem = "placa";
    break;
   case 1:
    campoOrdem = "modelo";
    break;
   case 2:
    campoOrdem = "ano";
    break;
  }
  SortOrder ordem = SortOrder.Descending;
  switch (cbOrdemTipo.getSelectedIndex()) {
   case 0:
    ordem = SortOrder.Ascending;
    break;
   case 1:
    ordem = SortOrder.Descending;
    break;
  }

  listaAutomoveis = BancoDados.tabelaAutomoveis.find(
          ObjectFilters.or(
                  ObjectFilters.regex("placa", "(?i)" + placaOuModelo),
                  ObjectFilters.regex("modelo", "(?i)" + placaOuModelo)
          ),
          FindOptions.sort(campoOrdem, ordem)
  ).toList();
 }

 public void preencheTabelaGrafica() {
  DefaultTableModel dtm = (DefaultTableModel) tabelaAutomoveis.getModel();
  dtm.setRowCount(0);
  for (Automovel a : listaAutomoveis) {
   dtm.addRow(new Object[]{a.id, a.placa, a.modelo, a.ano});
  }
 }

 public FrmAutomoveis(java.awt.Frame parent, boolean modal) {
  super(parent, modal);
  initComponents();
  //preencher a tabela
  filtraDadosAutomoveis("");
  preencheTabelaGrafica();
 }

 /**
  * This method is called from within the constructor to initialize the form.
  * WARNING: Do NOT modify this code. The content of this method is always
  * regenerated by the Form Editor.
  */
 @SuppressWarnings("unchecked")
 // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
 private void initComponents() {

  jPanel1 = new javax.swing.JPanel();
  jLabel1 = new javax.swing.JLabel();
  edtPlaca = new javax.swing.JTextField();
  jLabel2 = new javax.swing.JLabel();
  edtModelo = new javax.swing.JTextField();
  jLabel3 = new javax.swing.JLabel();
  edtAno = new javax.swing.JFormattedTextField();
  jButton1 = new javax.swing.JButton();
  jButton2 = new javax.swing.JButton();
  jButton3 = new javax.swing.JButton();
  cbProprietario = new javax.swing.JComboBox<>();
  jLabel7 = new javax.swing.JLabel();
  jScrollPane1 = new javax.swing.JScrollPane();
  tabelaAutomoveis = new javax.swing.JTable();
  jPanel2 = new javax.swing.JPanel();
  jLabel4 = new javax.swing.JLabel();
  edtPesquisa = new javax.swing.JTextField();
  jButton4 = new javax.swing.JButton();
  jButton5 = new javax.swing.JButton();
  cbOrdem = new javax.swing.JComboBox<>();
  jLabel5 = new javax.swing.JLabel();
  cbOrdemTipo = new javax.swing.JComboBox<>();
  jLabel6 = new javax.swing.JLabel();

  setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
  setTitle("Automóveis");

  jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), null));

  jLabel1.setText("Placa");

  jLabel2.setText("Modelo");

  jLabel3.setText("Ano");

  try {
   edtAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
  } catch (java.text.ParseException ex) {
   ex.printStackTrace();
  }

  jButton1.setText("Salvar");
  jButton1.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton1ActionPerformed(evt);
   }
  });

  jButton2.setText("Cancelar");
  jButton2.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton2ActionPerformed(evt);
   }
  });

  jButton3.setText("Excluir");
  jButton3.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton3ActionPerformed(evt);
   }
  });

  jLabel7.setText("Proprietário");

  javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
  jPanel1.setLayout(jPanel1Layout);
  jPanel1Layout.setHorizontalGroup(
   jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(jPanel1Layout.createSequentialGroup()
    .addContainerGap()
    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addGroup(jPanel1Layout.createSequentialGroup()
      .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(jLabel2)
        .addGap(0, 0, Short.MAX_VALUE))
       .addComponent(edtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addComponent(jLabel3)
       .addComponent(edtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
     .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addComponent(cbProprietario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addGroup(jPanel1Layout.createSequentialGroup()
      .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addComponent(jLabel1)
       .addComponent(edtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
       .addComponent(jLabel7))
      .addGap(0, 0, Short.MAX_VALUE)))
    .addContainerGap())
  );
  jPanel1Layout.setVerticalGroup(
   jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(jPanel1Layout.createSequentialGroup()
    .addContainerGap()
    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
     .addGroup(jPanel1Layout.createSequentialGroup()
      .addComponent(jLabel3)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(edtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
     .addGroup(jPanel1Layout.createSequentialGroup()
      .addComponent(jLabel1)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(edtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(jLabel2)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(edtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
    .addGap(11, 11, 11)
    .addComponent(jLabel7)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(cbProprietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addGap(38, 38, 38)
    .addComponent(jButton1)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jButton2)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(jButton3)
    .addContainerGap(166, Short.MAX_VALUE))
  );

  tabelaAutomoveis.setModel(new javax.swing.table.DefaultTableModel(
   new Object [][] {

   },
   new String [] {
    "Id", "Placa", "Modelo", "Ano"
   }
  ) {
   boolean[] canEdit = new boolean [] {
    false, false, false, false
   };

   public boolean isCellEditable(int rowIndex, int columnIndex) {
    return canEdit [columnIndex];
   }
  });
  tabelaAutomoveis.addMouseListener(new java.awt.event.MouseAdapter() {
   public void mouseClicked(java.awt.event.MouseEvent evt) {
    tabelaAutomoveisMouseClicked(evt);
   }
  });
  tabelaAutomoveis.addKeyListener(new java.awt.event.KeyAdapter() {
   public void keyReleased(java.awt.event.KeyEvent evt) {
    tabelaAutomoveisKeyReleased(evt);
   }
  });
  jScrollPane1.setViewportView(tabelaAutomoveis);
  if (tabelaAutomoveis.getColumnModel().getColumnCount() > 0) {
   tabelaAutomoveis.getColumnModel().getColumn(0).setMinWidth(0);
   tabelaAutomoveis.getColumnModel().getColumn(0).setPreferredWidth(0);
   tabelaAutomoveis.getColumnModel().getColumn(0).setMaxWidth(0);
  }

  jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

  jLabel4.setText("Digite placa ou modelo para pesquisar");

  edtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
   public void keyReleased(java.awt.event.KeyEvent evt) {
    edtPesquisaKeyReleased(evt);
   }
  });

  jButton4.setText("Pesquisar");
  jButton4.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton4ActionPerformed(evt);
   }
  });

  jButton5.setText("Limpar pesquisa");
  jButton5.addActionListener(new java.awt.event.ActionListener() {
   public void actionPerformed(java.awt.event.ActionEvent evt) {
    jButton5ActionPerformed(evt);
   }
  });

  cbOrdem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "placa", "modelo", "ano" }));
  cbOrdem.addItemListener(new java.awt.event.ItemListener() {
   public void itemStateChanged(java.awt.event.ItemEvent evt) {
    cbOrdemItemStateChanged(evt);
   }
  });

  jLabel5.setText("Ordenar por:");

  cbOrdemTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
  cbOrdemTipo.addItemListener(new java.awt.event.ItemListener() {
   public void itemStateChanged(java.awt.event.ItemEvent evt) {
    cbOrdemTipoItemStateChanged(evt);
   }
  });

  jLabel6.setText("Ordem");

  javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
  jPanel2.setLayout(jPanel2Layout);
  jPanel2Layout.setHorizontalGroup(
   jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(jPanel2Layout.createSequentialGroup()
    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGroup(jPanel2Layout.createSequentialGroup()
      .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addComponent(jLabel5)
       .addComponent(cbOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
       .addComponent(jLabel6)
       .addGroup(jPanel2Layout.createSequentialGroup()
        .addComponent(cbOrdemTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jButton5))))
     .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
     .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
    .addContainerGap(67, Short.MAX_VALUE))
  );
  jPanel2Layout.setVerticalGroup(
   jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(jPanel2Layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jLabel4)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
     .addGroup(jPanel2Layout.createSequentialGroup()
      .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
       .addComponent(jLabel5)
       .addComponent(jLabel6))
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
       .addComponent(cbOrdem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
       .addComponent(cbOrdemTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
     .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
      .addComponent(jButton4)
      .addComponent(jButton5)))
    .addContainerGap(42, Short.MAX_VALUE))
  );

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
  getContentPane().setLayout(layout);
  layout.setHorizontalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(layout.createSequentialGroup()
    .addContainerGap()
    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(jScrollPane1)
     .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    .addContainerGap())
  );
  layout.setVerticalGroup(
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
    .addContainerGap()
    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
     .addGroup(layout.createSequentialGroup()
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
    .addContainerGap())
  );

  pack();
  setLocationRelativeTo(null);
 }// </editor-fold>//GEN-END:initComponents

 private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

  automovel.placa = edtPlaca.getText();
  automovel.modelo = edtModelo.getText();
  automovel.ano = Integer.parseInt(edtAno.getText());
  //adiciona o proprietário ao veículo
  automovel.proprietario = (Pessoa) cbProprietario.getSelectedItem();

  Automovel autoPesqPlaca = selecionaPorPlaca(automovel.placa);

  if (autoPesqPlaca == null && automovel.id == null) {
   //insere
   BancoDados.tabelaAutomoveis.insert(automovel);
  } else if (autoPesqPlaca == null || (autoPesqPlaca.id.equals(automovel.id))) {
   //atualiza
   BancoDados.tabelaAutomoveis.update(automovel);
  } else {
   JOptionPane.showMessageDialog(null, "Placa já cadastrada!");
   return;
  }

  JOptionPane.showMessageDialog(null, "Registro salvo!");
  automovel = new Automovel();
  //limpar os campos
  limpaCampos();
  //preencher a tabela
  filtraDadosAutomoveis("");
  preencheTabelaGrafica();

 }//GEN-LAST:event_jButton1ActionPerformed

 private void tabelaAutomoveisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAutomoveisMouseClicked
  int linhaSelecionada = tabelaAutomoveis.getSelectedRow();
  if (linhaSelecionada >= 0) {
   DefaultTableModel dtm = (DefaultTableModel) tabelaAutomoveis.getModel();
   automovel.id = (NitriteId) dtm.getValueAt(linhaSelecionada, 0);
   automovel = BancoDados.tabelaAutomoveis.getById(automovel.id);
   edtAno.setText("" + automovel.ano);
   edtModelo.setText(automovel.modelo);
   edtPlaca.setText(automovel.placa);
   //seleciona o proprietário no combobox
   cbProprietario.setSelectedItem(automovel.proprietario);
  }
 }//GEN-LAST:event_tabelaAutomoveisMouseClicked

 private void tabelaAutomoveisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaAutomoveisKeyReleased
  tabelaAutomoveisMouseClicked(null);
 }//GEN-LAST:event_tabelaAutomoveisKeyReleased

 private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
  limpaCampos();
  automovel = new Automovel();
 }//GEN-LAST:event_jButton2ActionPerformed

 private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
  if (automovel.id != null) {
   int confirmacao = JOptionPane.showConfirmDialog(null, "Confirma exclusão?");
   if (confirmacao == JOptionPane.YES_OPTION) {
    BancoDados.tabelaAutomoveis.remove(automovel);
    JOptionPane.showMessageDialog(null, "Registro excluído");
    //cria um novo automóvel 'limpo'
    automovel = new Automovel();
    //limpar os campos
    limpaCampos();
    //preencher a tabela
    filtraDadosAutomoveis("");
    preencheTabelaGrafica();
   }
  }
 }//GEN-LAST:event_jButton3ActionPerformed

 private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
  String pesquisa = edtPesquisa.getText();
  //preencher a tabela
  filtraDadosAutomoveis(pesquisa);
  preencheTabelaGrafica();
 }//GEN-LAST:event_jButton4ActionPerformed

 private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
  edtPesquisa.setText("");
  //preencher a tabela
  filtraDadosAutomoveis("");
  preencheTabelaGrafica();
 }//GEN-LAST:event_jButton5ActionPerformed

 private void edtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtPesquisaKeyReleased
  jButton4ActionPerformed(null);
 }//GEN-LAST:event_edtPesquisaKeyReleased

 private void cbOrdemTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOrdemTipoItemStateChanged
  jButton4ActionPerformed(null);
 }//GEN-LAST:event_cbOrdemTipoItemStateChanged

 private void cbOrdemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOrdemItemStateChanged
  jButton4ActionPerformed(null);
 }//GEN-LAST:event_cbOrdemItemStateChanged


 // Variables declaration - do not modify//GEN-BEGIN:variables
 private javax.swing.JComboBox<String> cbOrdem;
 private javax.swing.JComboBox<String> cbOrdemTipo;
 private javax.swing.JComboBox<String> cbProprietario;
 private javax.swing.JFormattedTextField edtAno;
 private javax.swing.JTextField edtModelo;
 private javax.swing.JTextField edtPesquisa;
 private javax.swing.JTextField edtPlaca;
 private javax.swing.JButton jButton1;
 private javax.swing.JButton jButton2;
 private javax.swing.JButton jButton3;
 private javax.swing.JButton jButton4;
 private javax.swing.JButton jButton5;
 private javax.swing.JLabel jLabel1;
 private javax.swing.JLabel jLabel2;
 private javax.swing.JLabel jLabel3;
 private javax.swing.JLabel jLabel4;
 private javax.swing.JLabel jLabel5;
 private javax.swing.JLabel jLabel6;
 private javax.swing.JLabel jLabel7;
 private javax.swing.JPanel jPanel1;
 private javax.swing.JPanel jPanel2;
 private javax.swing.JScrollPane jScrollPane1;
 private javax.swing.JTable tabelaAutomoveis;
 // End of variables declaration//GEN-END:variables
}
