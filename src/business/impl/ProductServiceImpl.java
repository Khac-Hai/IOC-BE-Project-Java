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
    public void addProduct(Product product) {
        if (product == null) {
            throw new RuntimeException("Sản phẩm không được để trống");
        }
        if (product.getName() == null || product.getName().isBlank()) {
            throw new RuntimeException("Tên sản phẩm không được để trống");
        }

        // kiểm tra trùng tên bằng getAllProducts()
        List<Product> allProducts = productDAO.getAllProducts();
        for (Product p : allProducts) {
            if (p.getName().equalsIgnoreCase(product.getName())) {
                throw new RuntimeException("Tên sản phẩm đã tồn tại, không thể thêm trùng!");
            }
        }

        try {
            productDAO.addProduct(product);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi thêm sản phẩm: " + e.getMessage(), e);
        }
    }


    @Override
    public void updateProduct(Product product) {
        if (product == null) {
            throw new RuntimeException("Sản phẩm không được để trống");
        }
        try {
            productDAO.updateProduct(product);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật sản phẩm: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        if (id <= 0) {
            throw new RuntimeException("ID sản phẩm phải lớn hơn 0");
        }
        try {
            productDAO.deleteProduct(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa sản phẩm: " + e.getMessage(), e);
        }
    }

    @Override
    public Product findProductById(int id) {
        if (id <= 0) {
            throw new RuntimeException("ID sản phẩm phải lớn hơn 0");
        }
        try {
            return productDAO.getProductById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sản phẩm theo ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> listAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> searchByBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new RuntimeException("Thương hiệu không được để trống");
        }
        try {
            return productDAO.findByBrand(brand);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sản phẩm theo thương hiệu: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> searchByPriceRange(double min, double max) {
        if (min < 0 || max < 0 || min > max) {
            throw new RuntimeException("Khoảng giá không hợp lệ");
        }
        try {
            return productDAO.findByPriceRange(min, max);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sản phẩm theo khoảng giá: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> searchByStock(int stock) {
        if (stock < 0) {
            throw new RuntimeException("Số lượng tồn kho không được âm");
        }
        try {
            return productDAO.findByStock(stock);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sản phẩm theo tồn kho: " + e.getMessage(), e);
        }
    }
}
