package com.restassignment.employee;

import com.restassignment.order.Order;
import com.restassignment.order.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {


//    private final EmployeeRepository employeeRepository;
//    private final OrderRepository orderRepository;

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeService.getAllEmployees();
    }


    @PostMapping("/employees/new")
    public Employee newEmployee (@RequestBody Employee newEmployee) {
        return employeeService.createEmployee(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public Employee getOneEmployee (@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeService.updateEmployee(id, newEmployee);
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/{employeeId}/orders")
    public List<Order> getOrdersForEmployee(@PathVariable Long employeeId) {
        return employeeService.getOrdersForEmployee(employeeId);
    }

    @GetMapping("/employees/{employeeId}/totalOrders")
    public long getTotalOrdersForEmployee(@PathVariable Long employeeId) {
        return employeeService.getTotalOrdersForEmployee(employeeId);
    }


}
