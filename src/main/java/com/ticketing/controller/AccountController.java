package com.ticketing.controller;

import com.ticketing.entity.Employee;
import com.ticketing.entity.Role;
import com.ticketing.repository.EmployeeRepository;
import com.ticketing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class AccountController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/create-account")
    public String showCreateAccountForm() {
        return "create-account";
    }

    @PostMapping("/create-account")
    public String createAccount(
            @NotBlank(message = "First name is required") String firstName,
            @NotBlank(message = "Last name is required") String lastName,
            @NotBlank(message = "Contact phone is required") 
            @Pattern(regexp = "^\\d{10}$", message = "Contact phone must be a 10-digit number") String contactPhone,
            @Email(message = "Invalid email format") 
            @NotBlank(message = "Email is required") String username,
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{10,}$", 
                     message = "Password must be at least 10 characters and include 1 uppercase, 1 lowercase, 1 number, and 1 special character") 
            String password,
            String role,
            RedirectAttributes redirectAttributes
    ) {
        Optional<Employee> existingEmployee = employeeRepository.findByUsername(username);

        if (existingEmployee.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Username already exists.");
            return "redirect:/create-account";
        }

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setContactPhone(contactPhone);
        employee.setUsername(username);
        employee.setPassword(passwordEncoder.encode(password));
        employee.setEmail(username);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(role);
        if (userRole != null) {
            roles.add(userRole);
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid role selected.");
            return "redirect:/create-account";
        }

        employee.setRoles(roles);
        employeeRepository.save(employee);

        redirectAttributes.addFlashAttribute("success", "Account created successfully.");
        return "redirect:/login";
    }
}
