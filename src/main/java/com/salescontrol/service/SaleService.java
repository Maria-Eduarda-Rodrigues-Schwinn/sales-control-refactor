package com.salescontrol.service;

import com.salescontrol.data.sale.SaleDao;
import com.salescontrol.domain.Sale;
import com.salescontrol.domain.SaleProduct;
import com.salescontrol.service.CartService.CartItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleService {

    private final SaleDao saleDao = new SaleDao();
    private final ProductService productService = new ProductService(); // para buscar o produto

    public void finalizeSale(List<CartItem> cartItems) throws Exception {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalArgumentException("O carrinho está vazio.");
        }

        Sale sale = new Sale();
        sale.setSaleDate(new Date());

        double total = 0.0;
        List<SaleProduct> saleProducts = new ArrayList<>();

        for (CartItem item : cartItems) {
            int quantity = item.getQuantity();
            double unitPrice = item.getUnitPrice();
            total += quantity * unitPrice;

            com.salescontrol.domain.Product product = productService.getProductById(item.getProductId());
            if (product != null) {
                SaleProduct saleProduct = new SaleProduct(product, quantity, unitPrice);
                saleProducts.add(saleProduct);
            }
        }

        sale.setTotalValue(total);

        saleDao.save(sale, saleProducts);
    }
}
