create schema quanlydienthoai;

set search_path to quanlydienthoai;
-- 1. Bảng Admin (quản trị viên)
CREATE TABLE Admin (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

-- 2. Bảng Product (sản phẩm)
CREATE TABLE Product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         brand VARCHAR(50) NOT NULL,
                         price DECIMAL(12,2) NOT NULL,
                         stock INT NOT NULL
);

-- 3. Bảng Customer (khách hàng)
CREATE TABLE Customer (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          phone VARCHAR(20),
                          email VARCHAR(100) UNIQUE,
                          address VARCHAR(255)
);

-- 4. Bảng Invoice (hóa đơn)
CREATE TABLE Invoice (
                         id SERIAL PRIMARY KEY ,
                         customer_id INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         total_amount DECIMAL(12,2) NOT NULL,
                         FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

-- 5. Bảng Invoice_Details (chi tiết hóa đơn)
CREATE TABLE Invoice_Details (
                                 id SERIAL PRIMARY KEY,
                                 invoice_id INT,
                                 product_id INT,
                                 quantity INT NOT NULL,
                                 unit_price DECIMAL(12,2) NOT NULL,
                                 FOREIGN KEY (invoice_id) REFERENCES Invoice(id),
                                 FOREIGN KEY (product_id) REFERENCES Product(id)
);
