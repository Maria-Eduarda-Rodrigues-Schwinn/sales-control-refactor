package com.salescontrol.service;

import com.salescontrol.data.sale.SaleDao;
import com.salescontrol.domain.Sale;
import com.salescontrol.domain.SaleProduct;
import com.salescontrol.service.CartService.CartItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Sale> filterSales(Date fromDate, Date toDate, String searchedProductName, String selectedCategory) {
        List<Sale> allSales = saleDao.getAllSales();

        return allSales.stream().filter(sale -> {
            boolean dateCondition = true;
            if (fromDate != null) {
                dateCondition = !sale.getSaleDate().before(fromDate); // a data da venda deve ser igual ou depois de fromDate
            }
            if (toDate != null) {
                dateCondition = dateCondition && !sale.getSaleDate().after(toDate); // a data da venda deve ser igual ou antes de toDate
            }

            boolean categoryCondition = true;
            if (!"Todas".equalsIgnoreCase(selectedCategory)) {
                categoryCondition = sale.getProductsSold().stream()
                        .anyMatch(sp -> sp.getProduct().getCategory().getTranslation().equalsIgnoreCase(selectedCategory));
            }

            boolean nameCondition = true;
            if (searchedProductName != null && !searchedProductName.isEmpty()) {
                nameCondition = sale.getProductsSold().stream()
                        .anyMatch(sp -> sp.getProduct().getName().toLowerCase().contains(searchedProductName));
            }

            return dateCondition && categoryCondition && nameCondition;
        }).collect(Collectors.toList());
    }
}
