package com.restassignment.order;

import com.restassignment.employee.Employee;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Objects;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    //foreign key
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Order () {

    }

    public Order(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order order = (Order) o;
        return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
                && Objects.equals(this.status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
