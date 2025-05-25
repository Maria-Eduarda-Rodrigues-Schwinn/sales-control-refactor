package com.salescontrol.service;

import com.salescontrol.data.product.ProductDao;
import com.salescontrol.domain.Product;
import com.salescontrol.enuns.Category;
import com.salescontrol.enuns.UnitOfMeasure;
import com.salescontrol.utils.ValidationUtils;
import java.util.List;

public class ProductService {

    private final ProductDao productDao = new ProductDao();

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    public void updateProduct(int productId, double newUnitPrice, int newQuantity) {
        if (newUnitPrice <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }
        if (newQuantity < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa.");
        }

        Product product = productDao.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
        product.setUnitPrice(newUnitPrice);
        product.setQuantity(newQuantity);
        productDao.update(product);
    }

    public boolean deleteProduct(int productId) {
        return productDao.delete(productId);
    }

    public List<Product> searchProductsByName(String searchName) {
        return productDao.searchProductsByName(searchName.trim());
    }

    public void createProduct(String name, String categoryStr, double unitPrice, String unitOfMeasureStr, int quantity) {
        ValidationUtils.validateProductInputs(name, categoryStr, unitPrice, unitOfMeasureStr, quantity);

        Category category = null;
        for (Category c : Category.values()) {
            if (c.getTranslation().equals(categoryStr)) {
                category = c;
                break;
            }
        }

        UnitOfMeasure unitOfMeasure = null;
        for (UnitOfMeasure uom : UnitOfMeasure.values()) {
            if (uom.getTranslation().equals(unitOfMeasureStr)) {
                unitOfMeasure = uom;
                break;
            }
        }

        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setUnitPrice(unitPrice);
        product.setUnitOfMeasure(unitOfMeasure);
        product.setQuantity(quantity);

        productDao.save(product);
    }
}
