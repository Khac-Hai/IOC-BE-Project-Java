package presentation;

import model.Product;
import business.ProductService;

import java.util.Scanner;

public class ProductView {
    private ProductService productService;
    private Scanner scanner = new Scanner(System.in);

    public ProductView(ProductService productService) {
        this.productService = productService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("===== QUẢN LÝ SẢN PHẨM =====");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.print("Chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    productService.listAllProducts().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Tên sản phẩm: ");
                    String name = scanner.nextLine();
                    System.out.print("Hãng: ");
                    String brand = scanner.nextLine();
                    System.out.print("Giá: ");
                    double price = scanner.nextDouble();
                    System.out.print("Tồn kho: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    productService.addProduct(new Product(0, name, brand, price, stock));
                    System.out.println("Thêm sản phẩm thành công!");
                    break;
                case 3:
                    System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Product pUpdate = productService.findProductById(idUpdate);
                    if (pUpdate != null) {
                        System.out.print("Tên mới: ");
                        pUpdate.setName(scanner.nextLine());
                        System.out.print("Hãng mới: ");
                        pUpdate.setBrand(scanner.nextLine());
                        System.out.print("Giá mới: ");
                        pUpdate.setPrice(scanner.nextDouble());
                        System.out.print("Tồn kho mới: ");
                        pUpdate.setStock(scanner.nextInt());
                        scanner.nextLine();
                        productService.updateProduct(pUpdate);
                        System.out.println("Cập nhật thành công!");
                    } else {
                        System.out.println("Không tìm thấy sản phẩm!");
                    }
                    break;
                case 4:
                    System.out.print("Nhập ID sản phẩm cần xóa: ");
                    int idDelete = scanner.nextInt();
                    scanner.nextLine();
                    productService.deleteProduct(idDelete);
                    System.out.println("Xóa sản phẩm thành công!");
                    break;
                case 5:
                    System.out.print("Nhập tên hãng: ");
                    String brandSearch = scanner.nextLine();
                    productService.searchByBrand(brandSearch).forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Giá thấp nhất: ");
                    double min = scanner.nextDouble();
                    System.out.print("Giá cao nhất: ");
                    double max = scanner.nextDouble();
                    scanner.nextLine();
                    productService.searchByPriceRange(min, max).forEach(System.out::println);
                    break;
                case 7:
                    System.out.print("Nhập mức tồn kho tối đa: ");
                    int stockSearch = scanner.nextInt();
                    scanner.nextLine();
                    productService.searchByStock(stockSearch).forEach(System.out::println);
                    break;
                case 8:
                    return;
            }
        }
    }
}
