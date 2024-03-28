package com.furcare.furcare.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.furcare.furcare.models.entities.Citas;
import com.furcare.furcare.service.ICitasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private ICitasService citasService;

    @GetMapping
    public ResponseEntity<List<Citas>> listarCitas(){
        return new ResponseEntity<List<Citas>>(citasService.listarCitas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearCitas(@Valid @RequestBody Citas cita, BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(citasService.crearCita(cita));
    }

    @PutMapping("/editarCita/{idCita}")
    public ResponseEntity<Citas> editarCita(@PathVariable(name = "idCita") Long idCita, @RequestBody Citas citaActualizada){
        Citas cita = citasService.editarCita(idCita, citaActualizada);
        if (cita == null) {
            throw new UnsupportedOperationException("no existe la cita"+ idCita);
        }
        return ResponseEntity.ok(cita);
    }

    @PutMapping("/finalizarCita/{idCita}")
    public ResponseEntity<Citas> finalizarCita(@PathVariable(name = "idCita") Long idCita){
        Citas cita = citasService.finalizarCita(idCita);
        if (cita == null) {
            throw new UnsupportedOperationException("no existe la cita"+ idCita);
        }
        return ResponseEntity.ok(cita);
    } 

    @DeleteMapping("/cancelarCita/{idCita}")
    public ResponseEntity<Void> cancelarCita(@PathVariable(name = "idCita") Long idCita){
        citasService.cancelarCita(idCita);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<?> validation(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error ->{
            errors.put(error.getField(), "el campo" + error.getField()+ "" + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
