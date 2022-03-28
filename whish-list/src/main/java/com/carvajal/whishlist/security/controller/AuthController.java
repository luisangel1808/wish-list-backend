package com.carvajal.whishlist.security.controller;

import com.carvajal.whishlist.dao.AdminDao;
import com.carvajal.whishlist.dao.CustomerDao;
import com.carvajal.whishlist.model.Admin;
import com.carvajal.whishlist.model.Customer;
import com.carvajal.whishlist.security.dto.JwtDto;
import com.carvajal.whishlist.security.dto.LoginUser;
import com.carvajal.whishlist.security.dto.NewUser;
import com.carvajal.whishlist.security.entity.Role;
import com.carvajal.whishlist.security.entity.User;
import com.carvajal.whishlist.security.enums.RoleName;
import com.carvajal.whishlist.security.jwt.JwtProvider;
import com.carvajal.whishlist.security.service.RoleService;
import com.carvajal.whishlist.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    AdminDao adminDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (userService.existsByUserName(newUser.getName()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        if (userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        User user =
                new User(newUser.getName(), newUser.getUsername(), newUser.getEmail(),
                        passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        String intRole = "";
        if (newUser.getRoles().contains("admin")) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN).get());
            intRole = "A";
        } else if (newUser.getRoles().contains("customer")) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_CUSTOMER).get());
            intRole = "C";
        }
        user.setRoles(roles);
        int id = userService.save(user);

        if (intRole.equals("C")) {
            Customer customer = new Customer();
            customer.setUser(user);
            customerDao.insert(customer);
        }
        if (intRole.equals("A")) {
            Admin admin = new Admin();
            admin.setUser(user);
            adminDao.insert(admin);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword())));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}