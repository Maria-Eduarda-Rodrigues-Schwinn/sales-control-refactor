package com.salescontrol.ui;

import com.salescontrol.data.product.ProductTableModel;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import com.salescontrol.domain.Product;
import com.salescontrol.domain.User;
import com.salescontrol.enuns.UserType;
import com.salescontrol.service.CartService;
import com.salescontrol.service.ProductService;
import com.salescontrol.service.SaleService;
import java.awt.HeadlessException;
import javax.swing.SwingUtilities;

public class RegisterSale extends javax.swing.JFrame {

    private final User currentUser;

    private final ProductService productService = new ProductService();
    private final CartService cartService = new CartService();
    private final SaleService saleService = new SaleService();

    public RegisterSale(User user) {
        this.currentUser = user;
        initComponents();
        setPermissions();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistrationSale = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        scrollPaneProductsTable = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        txtProductQuantity = new javax.swing.JTextField();
        btnAddProductToCart = new javax.swing.JButton();
        scrollPaneCartProducts = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        lblProductsTitle = new javax.swing.JLabel();
        lblCartTitle = new javax.swing.JLabel();
        btnFinalizeSale = new javax.swing.JButton();
        btnClearCart = new javax.swing.JButton();
        btnRemoveSelectedItemFromCartTable = new javax.swing.JButton();
        lblTotalCartValue = new javax.swing.JLabel();
        btnCalculateTotal = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menu = new javax.swing.JMenu();
        itRegisterProduct = new javax.swing.JMenuItem();
        itEditProduct = new javax.swing.JMenuItem();
        itSalesReport = new javax.swing.JMenuItem();
        itLeave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar venda");

        pnlRegistrationSale.setBackground(new java.awt.Color(196, 211, 231));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sale-logo.png"))); // NOI18N

        scrollPaneProductsTable.setToolTipText("");
        scrollPaneProductsTable.setFont(new java.awt.Font("Bitstream Charter", 0, 14)); // NOI18N

        tblProducts.setFont(new java.awt.Font("Bitstream Charter", 0, 14)); // NOI18N
        tblProducts.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "Categoria", "Preço Unitário (R$)", "Unidade de Medida", "Quantidade"}
        ) {
            Class[] columnTypes = new Class [] {
                Integer.class, String.class, String.class, Double.class, String.class, Integer.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        scrollPaneProductsTable.setViewportView(tblProducts);

        txtProductQuantity.setFont(new java.awt.Font("Bitstream Charter", 0, 14)); // NOI18N
        txtProductQuantity.setToolTipText("Campo para preencher com a quantidade do produto selecionado para adicionar ao carrinho");
        txtProductQuantity.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quantidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bitstream Charter", 0, 14))); // NOI18N

        btnAddProductToCart.setBackground(new java.awt.Color(225, 234, 246));
        btnAddProductToCart.setFont(new java.awt.Font("Bitstream Charter", 0, 15)); // NOI18N
        btnAddProductToCart.setText("Adicionar");
        btnAddProductToCart.setToolTipText("Botão para adicionar uma quantidade de produto ao carrinho");
        btnAddProductToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductToCartActionPerformed(evt);
            }
        });

        scrollPaneCartProducts.setFont(new java.awt.Font("Bitstream Charter", 0, 14)); // NOI18N

        tblCart.setFont(new java.awt.Font("Bitstream Charter", 0, 14)); // NOI18N
        tblCart.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Nome", "Categoria", "Quantidade", "Preço Unitário (R$)", "Unidade de Medida"}
        ) {
            Class[] columnTypes = new Class[] {
                Integer.class, String.class, String.class, Double.class, String.class, Integer.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }
    );
    scrollPaneCartProducts.setViewportView(tblCart);

    lblProductsTitle.setFont(new java.awt.Font("Bitstream Charter", 1, 36)); // NOI18N
    lblProductsTitle.setText("Produtos");

    lblCartTitle.setFont(new java.awt.Font("Bitstream Charter", 1, 36)); // NOI18N
    lblCartTitle.setText("Carrinho");

    btnFinalizeSale.setBackground(new java.awt.Color(112, 199, 112));
    btnFinalizeSale.setFont(new java.awt.Font("Bitstream Charter", 0, 30)); // NOI18N
    btnFinalizeSale.setText("Finalizar");
    btnFinalizeSale.setToolTipText("Botão para finalizar venda");
    btnFinalizeSale.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnFinalizeSaleActionPerformed(evt);
        }
    });

    btnClearCart.setFont(new java.awt.Font("Bitstream Charter", 0, 24)); // NOI18N
    btnClearCart.setText("Limpar carrinho");
    btnClearCart.setToolTipText("Botão para cancelar ");
    btnClearCart.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnClearCartActionPerformed(evt);
        }
    });

    btnRemoveSelectedItemFromCartTable.setBackground(new java.awt.Color(255, 102, 102));
    btnRemoveSelectedItemFromCartTable.setFont(new java.awt.Font("Bitstream Charter", 0, 14)); // NOI18N
    btnRemoveSelectedItemFromCartTable.setText("Remover prouduto do carrinho");
    btnRemoveSelectedItemFromCartTable.setToolTipText("Boto para remover produto selecionado do carrinho~");
    btnRemoveSelectedItemFromCartTable.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnRemoveSelectedItemFromCartTableActionPerformed(evt);
        }
    });

    lblTotalCartValue.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N

    btnCalculateTotal.setBackground(new java.awt.Color(225, 234, 246));
    btnCalculateTotal.setFont(new java.awt.Font("Bitstream Charter", 0, 14)); // NOI18N
    btnCalculateTotal.setText("Calcular valor total");
    btnCalculateTotal.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCalculateTotalActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout pnlRegistrationSaleLayout = new javax.swing.GroupLayout(pnlRegistrationSale);
    pnlRegistrationSale.setLayout(pnlRegistrationSaleLayout);
    pnlRegistrationSaleLayout.setHorizontalGroup(
        pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
            .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegistrationSaleLayout.createSequentialGroup()
                    .addGap(304, 304, 304)
                    .addComponent(lblProductsTitle)
                    .addGap(166, 166, 166)
                    .addComponent(lblLogo)
                    .addGap(170, 170, 170)
                    .addComponent(lblCartTitle)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                    .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalCartValue, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                            .addGap(106, 106, 106)
                            .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                                    .addComponent(txtProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistrationSaleLayout.createSequentialGroup()
                                    .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnClearCart)
                                        .addComponent(scrollPaneProductsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(38, 38, 38)))
                            .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrollPaneCartProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                                    .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnCalculateTotal)
                                        .addComponent(btnFinalizeSale, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(217, 217, 217)
                                    .addComponent(btnRemoveSelectedItemFromCartTable)))))
                    .addGap(63, 63, 63)))
            .addContainerGap(6, Short.MAX_VALUE))
        .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
            .addGap(337, 337, 337)
            .addComponent(btnAddProductToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    pnlRegistrationSaleLayout.setVerticalGroup(
        pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
            .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                    .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(lblLogo)
                            .addGap(38, 38, 38))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistrationSaleLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblCartTitle)
                            .addGap(59, 59, 59)))
                    .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(scrollPaneProductsTable, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                        .addComponent(scrollPaneCartProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGroup(pnlRegistrationSaleLayout.createSequentialGroup()
                    .addGap(152, 152, 152)
                    .addComponent(lblProductsTitle)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(btnRemoveSelectedItemFromCartTable)
                .addComponent(txtProductQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnCalculateTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(13, 13, 13)
            .addComponent(btnAddProductToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTotalCartValue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(9, 9, 9)
            .addGroup(pnlRegistrationSaleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnClearCart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnFinalizeSale, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(139, 139, 139))
    );

    menuBar.setBackground(new java.awt.Color(255, 255, 255));

    menu.setText("Menu");
    menu.setFont(new java.awt.Font("Bitstream Charter", 0, 15)); // NOI18N

    itRegisterProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
    itRegisterProduct.setFont(new java.awt.Font("Bitstream Charter", 0, 15)); // NOI18N
    itRegisterProduct.setText("Cadastrar Produto");
    itRegisterProduct.setToolTipText("Opção para navegar até a tela de registrar produto");
    itRegisterProduct.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itRegisterProductActionPerformed(evt);
        }
    });
    menu.add(itRegisterProduct);

    itEditProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
    itEditProduct.setFont(new java.awt.Font("Bitstream Charter", 0, 15)); // NOI18N
    itEditProduct.setText("Editar Produto");
    itEditProduct.setToolTipText("Opção para navegar até a tela de editar produto");
    itEditProduct.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itEditProductActionPerformed(evt);
        }
    });
    menu.add(itEditProduct);

    itSalesReport.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
    itSalesReport.setFont(new java.awt.Font("Bitstream Charter", 0, 15)); // NOI18N
    itSalesReport.setText("Relatório de Vendas");
    itSalesReport.setToolTipText("Opção para navegar até a tela de relatório de vendas");
    itSalesReport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itSalesReportActionPerformed(evt);
        }
    });
    menu.add(itSalesReport);

    itLeave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
    itLeave.setFont(new java.awt.Font("Bitstream Charter", 0, 15)); // NOI18N
    itLeave.setText("Sair");
    itLeave.setToolTipText("Opção para efetuar logout");
    itLeave.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itLeaveActionPerformed(evt);
        }
    });
    menu.add(itLeave);

    menuBar.add(menu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(pnlRegistrationSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(pnlRegistrationSale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void itRegisterProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itRegisterProductActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new RegisterProduct(currentUser).setVisible(true);
        });
        this.setVisible(false);
    }//GEN-LAST:event_itRegisterProductActionPerformed

    private void itEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itEditProductActionPerformed
        EditProduct editProductScreen = new EditProduct(currentUser);
        editProductScreen.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_itEditProductActionPerformed

    private void itSalesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itSalesReportActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new SalesReport(currentUser).setVisible(true);
        });
        this.setVisible(false);
    }//GEN-LAST:event_itSalesReportActionPerformed

    private void itLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itLeaveActionPerformed
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");

        int response = JOptionPane.showConfirmDialog(this, "Deseja sair?", "Logout", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            java.awt.EventQueue.invokeLater(() -> {
                new Login().setVisible(true);
            });
            this.setVisible(false);
        }
    }//GEN-LAST:event_itLeaveActionPerformed

    private void btnAddProductToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductToCartActionPerformed
        int[] selectedRows = tblProducts.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um produto para adicionar ao carrinho.");
            return;
        } else if (selectedRows.length > 1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione apenas um produto para adicionar ao carrinho.");
            return;
        }

        int selectedRow = selectedRows[0];
        String quantityText = txtProductQuantity.getText().trim();
        txtProductQuantity.setText("");
        if (quantityText.isEmpty() || quantityText.equals("0")) {
            JOptionPane.showMessageDialog(this, "Por favor, insira uma quantidade válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "A quantidade deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object productIdObject = tblProducts.getValueAt(selectedRow, 0);
        int productId = Integer.parseInt(productIdObject.toString());

        try {
            cartService.addProductToCart(productId, quantity);
            loadProductTable();
            updateCartTable();

            JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho!");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddProductToCartActionPerformed

    private void btnFinalizeSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizeSaleActionPerformed
        if (cartService.isCartEmpty()) {
            JOptionPane.showMessageDialog(this, "O carrinho está vazio. Adicione produtos antes de finalizar a venda.");
            return;
        }

        try {
            saleService.finalizeSale(cartService.getCartItems());
            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");

            cartService.clearTemporaryCartAndRestoreStock();
            updateCartTable();
            loadProductTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar venda: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFinalizeSaleActionPerformed

    private void btnClearCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearCartActionPerformed
        try {
            cartService.clearTemporaryCartAndRestoreStock();
            updateCartTable();
            loadProductTable();
            JOptionPane.showMessageDialog(this, "Carrinho limpo!");
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao limpar o carrinho: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnClearCartActionPerformed

    private void btnRemoveSelectedItemFromCartTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSelectedItemFromCartTableActionPerformed
        int selectedRow = tblCart.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um produto para remover do carrinho.");
            return;
        }

        Object productIdObject = tblCart.getValueAt(selectedRow, 0);
        int productId = Integer.parseInt(productIdObject.toString());
        Object quantityObject = tblCart.getValueAt(selectedRow, 3);
        int quantity = Integer.parseInt(quantityObject.toString());

        try {
            cartService.removeItemFromCart(productId, quantity);
            updateCartTable();
            loadProductTable();
            JOptionPane.showMessageDialog(this, "Produto removido do carrinho!");
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao remover o item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveSelectedItemFromCartTableActionPerformed

    private void btnCalculateTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateTotalActionPerformed
        double total = cartService.calculateTotal();
        lblTotalCartValue.setText("Valor Total: R$ " + String.format("%.2f", total));
    }//GEN-LAST:event_btnCalculateTotalActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegisterSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new RegisterSale(new User()).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProductToCart;
    private javax.swing.JButton btnCalculateTotal;
    private javax.swing.JButton btnClearCart;
    private javax.swing.JButton btnFinalizeSale;
    private javax.swing.JButton btnRemoveSelectedItemFromCartTable;
    private javax.swing.JMenuItem itEditProduct;
    private javax.swing.JMenuItem itLeave;
    private javax.swing.JMenuItem itRegisterProduct;
    private javax.swing.JMenuItem itSalesReport;
    private javax.swing.JLabel lblCartTitle;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblProductsTitle;
    private javax.swing.JLabel lblTotalCartValue;
    private javax.swing.JMenu menu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel pnlRegistrationSale;
    private javax.swing.JScrollPane scrollPaneCartProducts;
    private javax.swing.JScrollPane scrollPaneProductsTable;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtProductQuantity;
    // End of variables declaration//GEN-END:variables

    public void updateCartTable() {
        DefaultTableModel cartModel = cartService.getCartTableModel();
        tblCart.setModel(cartModel);
    }

    @Override
    public void setVisible(boolean b) {
        if (!b) {
            cartService.clearTemporaryCartAndRestoreStock();
        }
        super.setVisible(b);
        if (b) {
            loadProductTable();
        }
    }

    private void setPermissions() {
        if (currentUser.getUserType() == UserType.EMPLOYEE) {
            itRegisterProduct.setEnabled(false);
            itEditProduct.setEnabled(false);
            itSalesReport.setEnabled(false);
        }
    }

    private void loadProductTable() {
        List<Product> productList = productService.getAllProducts();
        SwingUtilities.invokeLater(() -> {
            tblProducts.setModel(new ProductTableModel(productList));
        });
    }
}
