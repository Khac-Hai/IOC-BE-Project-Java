package dao;

import model.Product;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    Product getProductById(int id);
    List<Product> getAllProducts();
    List<Product> findByBrand(String brand);
    List<Product> findByPriceRange(double min, double max);
    List<Product> findByStock(int stock);
}
