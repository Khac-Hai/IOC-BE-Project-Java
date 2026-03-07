package business;

import model.Product;
import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
    Product findProductById(int id);
    List<Product> listAllProducts();
    List<Product> searchByBrand(String brand);
    List<Product> searchByPriceRange(double min, double max);
    List<Product> searchByStock(int stock);
}

