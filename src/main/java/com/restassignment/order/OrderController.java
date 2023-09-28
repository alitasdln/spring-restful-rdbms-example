package com.restassignment.order;

import com.restassignment.employee.Employee;
import com.restassignment.employee.EmployeeNotFoundException;
import com.restassignment.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> all() {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders/new")
    public Order newOrder(@RequestBody Map<String, Object> orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/byEmployee/{employeeId}")
    public List<Order> getOrdersByEmployeeId(@PathVariable Long employeeId) {
        return orderService.getOrdersByEmployeeId(employeeId);
    }


}
