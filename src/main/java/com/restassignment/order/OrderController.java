package com.restassignment.order;

import com.restassignment.employee.Employee;
import com.restassignment.employee.EmployeeNotFoundException;
import com.restassignment.employee.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    public OrderController(OrderRepository orderRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/orders")
    public List<Order> all() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders/new")
    public Order newOrder(@RequestBody Map<String, Object> orderRequest) {
        String description = (String) orderRequest.get("description");
        Status status = Status.valueOf((String) orderRequest.get("status"));
        Long employeeId = Long.valueOf(orderRequest.get("employee_id").toString());

        // Retrieve the associated Employee based on the provided employee_id
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        // Create a new Order and associate it with the Employee
        Order newOrder = new Order(description, status);
        newOrder.setEmployee(employee);

        return orderRepository.save(newOrder);
    }

    @GetMapping("/orders/{id}")
    public Order newOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }


}
