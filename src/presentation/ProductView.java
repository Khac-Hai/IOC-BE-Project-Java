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

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        productService.listAllProducts().forEach(System.out::println);
                        break;
                    case 2:
                        // nhập tên sản phẩm, kiểm tra trùng
                        String name;
                        while (true) {
                            System.out.print("Tên sản phẩm: ");
                            name = scanner.nextLine();

                            final String finalName = name; // tạo biến final để dùng trong lambda
                            boolean exists = productService.listAllProducts().stream()
                                    .anyMatch(p -> p.getName().equalsIgnoreCase(finalName));

                            if (exists) {
                                System.out.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại!");
                            } else if (name.isBlank()) {
                                System.out.println("Tên sản phẩm không được để trống, vui lòng nhập lại!");
                            } else {
                                break;
                            }
                        }


                        System.out.print("Hãng: ");
                        String brand = scanner.nextLine();

                        double price;
                        while (true) {
                            System.out.print("Giá: ");
                            try {
                                price = Double.parseDouble(scanner.nextLine());
                                if (price < 0) {
                                    System.out.println("Giá không được âm!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Giá không hợp lệ, vui lòng nhập lại!");
                            }
                        }

                        int stock;
                        while (true) {
                            System.out.print("Tồn kho: ");
                            try {
                                stock = Integer.parseInt(scanner.nextLine());
                                if (stock < 0) {
                                    System.out.println("Tồn kho không được âm!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Tồn kho không hợp lệ, vui lòng nhập lại!");
                            }
                        }

                        try {
                            productService.addProduct(new Product(0, name, brand, price, stock));
                            System.out.println("Thêm sản phẩm thành công!");
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
                        int idUpdate;
                        try {
                            idUpdate = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ!");
                            break;
                        }
                        Product pUpdate = productService.findProductById(idUpdate);
                        if (pUpdate == null) {
                            System.out.println("Không tìm thấy sản phẩm với ID này!");
                            break;
                        }
                        System.out.println("Thông tin hiện tại: " + pUpdate);
                        System.out.print("Tên mới: ");
                        pUpdate.setName(scanner.nextLine());
                        System.out.print("Hãng mới: ");
                        pUpdate.setBrand(scanner.nextLine());
                        try {
                            System.out.print("Giá mới: ");
                            pUpdate.setPrice(Double.parseDouble(scanner.nextLine()));
                            System.out.print("Tồn kho mới: ");
                            pUpdate.setStock(Integer.parseInt(scanner.nextLine()));
                        } catch (NumberFormatException e) {
                            System.out.println("Giá hoặc tồn kho không hợp lệ!");
                            break;
                        }
                        try {
                            productService.updateProduct(pUpdate);
                            System.out.println("Cập nhật thành công!");
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("Nhập ID sản phẩm cần xóa: ");
                        int idDelete;
                        try {
                            idDelete = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("ID không hợp lệ!");
                            break;
                        }
                        Product pDelete = productService.findProductById(idDelete);
                        if (pDelete == null) {
                            System.out.println("Không tìm thấy sản phẩm với ID này!");
                            break;
                        }
                        System.out.println("Bạn có chắc chắn muốn xóa sản phẩm: " + pDelete + " ? (Y/N)");
                        String confirm = scanner.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            try {
                                productService.deleteProduct(idDelete);
                                System.out.println("Xóa sản phẩm thành công!");
                            } catch (RuntimeException e) {
                                System.out.println("Lỗi: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Hủy xóa sản phẩm.");
                        }
                        break;

                    case 5:
                        System.out.print("Nhập tên hãng: ");
                        String brandSearch = scanner.nextLine();
                        try {
                            productService.searchByBrand(brandSearch).forEach(System.out::println);
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 6:
                        try {
                            System.out.print("Giá thấp nhất: ");
                            double min = Double.parseDouble(scanner.nextLine());
                            System.out.print("Giá cao nhất: ");
                            double max = Double.parseDouble(scanner.nextLine());
                            productService.searchByPriceRange(min, max).forEach(System.out::println);
                        } catch (NumberFormatException e) {
                            System.out.println("Giá nhập không hợp lệ!");
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 7:
                        try {
                            System.out.print("Nhập mức tồn kho tối đa: ");
                            int stockSearch = Integer.parseInt(scanner.nextLine());
                            productService.searchByStock(stockSearch).forEach(System.out::println);
                        } catch (NumberFormatException e) {
                            System.out.println("Tồn kho nhập không hợp lệ!");
                        } catch (RuntimeException e) {
                            System.out.println("Lỗi: " + e.getMessage());
                        }
                        break;

                    case 8:
                        return; // quay lại menu chính

                    default:
                        System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }
    }
}
