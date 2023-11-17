package dev.isudha.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.isudha.userservice.models.Role;
import dev.isudha.userservice.repository.RoleRepository;
import dev.isudha.userservice.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping("")
    public ResponseEntity<Role> createRole(@RequestBody String name){
        Role role = roleService.createRole(name);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

}
