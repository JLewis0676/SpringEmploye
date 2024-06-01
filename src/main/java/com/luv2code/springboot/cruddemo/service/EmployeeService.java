package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.repo.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    //using JPA Repository now
//    private EmployeeDAO employeeDAO;

    private IEmployeeRepository employeeRepository;

    //everywhere employeeDAO is we will change to employeeRepository
    @Autowired
    public EmployeeService(IEmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }else{
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theEmployee;
    }

//    @Transactional
    @Override
    public Employee save(Employee theEmployee) {
        return (Employee) employeeRepository.save(theEmployee);
    }
//    @Transactional
    @Override
    public void deleteById(int id) {
        this.employeeRepository.deleteById(id);
    }
}
