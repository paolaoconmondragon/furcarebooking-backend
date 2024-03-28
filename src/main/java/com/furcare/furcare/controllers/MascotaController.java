package com.furcare.furcare.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furcare.furcare.models.entities.Mascota;
import com.furcare.furcare.service.IMascotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private IMascotaService mascotaService;

    @PostMapping
    public ResponseEntity<?> crearMascota(@Valid @RequestBody Mascota mascota, BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.crearMascota(mascota));
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error ->{
            errors.put(error.getField(), "el campo" + error.getField()+ "" + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
