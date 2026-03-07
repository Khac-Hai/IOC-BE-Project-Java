package business.impl;

import dao.ProductDAO;
import model.Product;
import business.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void addProduct(Product product) { productDAO.addProduct(product); }

    @Override
    public void updateProduct(Product product) { productDAO.updateProduct(product); }

    @Override
    public void deleteProduct(int id) { productDAO.deleteProduct(id); }

    @Override
    public Product findProductById(int id) { return productDAO.getProductById(id); }

    @Override
    public List<Product> listAllProducts() { return productDAO.getAllProducts(); }

    @Override
    public List<Product> searchByBrand(String brand) { return productDAO.findByBrand(brand); }

    @Override
    public List<Product> searchByPriceRange(double min, double max) { return productDAO.findByPriceRange(min, max); }

    @Override
    public List<Product> searchByStock(int stock) { return productDAO.findByStock(stock); }
}

