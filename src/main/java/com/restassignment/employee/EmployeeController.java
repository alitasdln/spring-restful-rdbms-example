package com.restassignment.employee;

import com.restassignment.order.Order;
import com.restassignment.order.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final OrderRepository orderRepository;


    public EmployeeController(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeRepository.findAll();
    }

    @PostMapping("/employees/new")
    public Employee newEmployee (@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public Employee getOneEmployee (@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                }).orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    @GetMapping("/employees/{employeeId}/orders")
    public List<Order> getOrdersForEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        return orderRepository.findByEmployee(employee);
    }

    @GetMapping("/employees/{employeeId}/totalOrders")
    public long getTotalOrdersForEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        List<Order> orders = orderRepository.findByEmployee(employee);

        long totalOrders = orders.size();

        return totalOrders;
    }

}
