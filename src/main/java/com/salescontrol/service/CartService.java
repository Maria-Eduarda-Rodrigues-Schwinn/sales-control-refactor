package com.salescontrol.service;

import com.salescontrol.data.product.ProductDao;
import com.salescontrol.domain.Product;
import com.salescontrol.utils.DataManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class CartService {

    private final ProductDao productDao = new ProductDao();

    public void addProductToCart(int productId, int quantity) throws IllegalArgumentException {
        Product product = productDao.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("A quantidade solicitada é maior que o estoque disponível.");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productDao.update(product);
        DataManager.getInstance().addToTemporaryCart(productId, quantity);
    }

    public boolean isCartEmpty() {
        return DataManager.getInstance().getTemporaryCart().isEmpty();
    }

    public List<CartItem> getCartItems() {
        Map<Integer, Integer> cartMap = DataManager.getInstance().getTemporaryCart();
        List<CartItem> items = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : cartMap.entrySet()) {
            int productId = entry.getKey();
            int qty = entry.getValue();
            Product product = productDao.getProductById(productId);
            if (product != null) {
                CartItem item = new CartItem(
                        productId,
                        product.getName(),
                        product.getCategory().getTranslation(),
                        qty,
                        product.getUnitPrice(),
                        product.getUnitOfMeasure().getTranslation()
                );
                items.add(item);
            }
        }
        return items;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Integer, Integer> entry : DataManager.getInstance().getTemporaryCart().entrySet()) {
            int productId = entry.getKey();
            int qty = entry.getValue();
            Product product = productDao.getProductById(productId);
            if (product != null) {
                total += qty * product.getUnitPrice();
            }
        }
        return total;
    }

    public void clearTemporaryCartAndRestoreStock() {
        Map<Integer, Integer> cart = DataManager.getInstance().getTemporaryCart();
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            int productId = entry.getKey();
            int qty = entry.getValue();
            Product product = productDao.getProductById(productId);
            if (product != null) {
                product.setQuantity(product.getQuantity() + qty);
                productDao.update(product);
            }
        }
        DataManager.getInstance().clearTemporaryCart();
    }

    public void removeItemFromCart(int productId, int quantity) throws IllegalArgumentException {
        Map<Integer, Integer> cart = DataManager.getInstance().getTemporaryCart();
        if (!cart.containsKey(productId)) {
            throw new IllegalArgumentException("Item não existe no carrinho.");
        }
        int currentQty = cart.get(productId);
        if (quantity > currentQty) {
            throw new IllegalArgumentException("Quantidade para remoção maior que a existente no carrinho.");
        }
        if (quantity == currentQty) {
            cart.remove(productId);
        } else {
            cart.put(productId, currentQty - quantity);
        }
        // Reintegra a quantidade removida ao estoque
        Product product = productDao.getProductById(productId);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantity);
            productDao.update(product);
        }
    }

    public DefaultTableModel getCartTableModel() {
        String[] columnNames = {"ID", "Nome", "Categoria", "Quantidade", "Preço Unitário", "Unidade de Medida"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (CartItem item : getCartItems()) {
            Object[] row = {
                item.getProductId(),
                item.getProductName(),
                item.getCategory(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getUnitOfMeasure()
            };
            model.addRow(row);
        }
        return model;
    }

    public static class CartItem {

        private int productId;
        private String productName;
        private String category;
        private int quantity;
        private double unitPrice;
        private String unitOfMeasure;

        public CartItem(int productId, String productName, String category, int quantity, double unitPrice, String unitOfMeasure) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.unitOfMeasure = unitOfMeasure;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public String getCategory() {
            return category;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public String getUnitOfMeasure() {
            return unitOfMeasure;
        }
    }
}
