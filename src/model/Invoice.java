package model;

// 4. Invoice
import java.time.LocalDateTime;

public class Invoice {
    private int id;
    private Customer customer;
    private LocalDateTime createdAt;
    private double totalAmount;

    public Invoice() {}
    public Invoice(int id, Customer customer, LocalDateTime createdAt, double totalAmount) {
        this.id = id;
        this.customer = customer;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
