package com.restassignment.order;

import com.restassignment.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByEmployee(Employee employee);
    List<Order> findByEmployeeId(Long employeeId);
}
