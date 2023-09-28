package com.restassignment.order;

import com.restassignment.employee.Employee;
import com.restassignment.employee.EmployeeNotFoundException;
import com.restassignment.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;

    public OrderService(OrderRepository orderRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(Map<String, Object> orderRequest) {
        String description = (String) orderRequest.get("description");
        Status status = Status.valueOf((String) orderRequest.get("status"));
        Long employeeId = Long.valueOf(orderRequest.get("employee_id").toString());

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        Order newOrder = new Order(description, status);
        newOrder.setEmployee(employee);

        return orderRepository.save(newOrder);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<Order> getOrdersByEmployeeId(Long employeeId) {
        return orderRepository.findByEmployeeId(employeeId);
    }
}