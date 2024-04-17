package com.furcare.furcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.furcare.furcare.models.entities.Citas;
import com.furcare.furcare.repositories.CitaRepository;

@Service
public class CitasServiceImpl implements ICitasService{

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Citas crearCita(Citas cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Citas editarCita(Long idCita, Citas cita) {
        Optional<Citas> resultado = citaRepository.findById(idCita);
        if (resultado.isPresent()) {
            Citas citaActualizada = resultado.get();
            citaActualizada.setFecha(cita.getFecha());
            citaActualizada.setHora(cita.getHora());
            return citaRepository.save(citaActualizada);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La cita con el $id no existe", idCita));
        }
    }

    @Override
    public List<Citas> listarCitas() {
        return (List<Citas>) citaRepository.findAll();
    }

    @Override
    public Citas finalizarCita(Long idCita) {
        Optional<Citas> resultado = citaRepository.findById(idCita);
        if (resultado.isPresent()) {
            Citas citaFinalizada = resultado.get();
            citaFinalizada.setEstado("F");
            return citaRepository.save(citaFinalizada);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La cita con el $id no existe", idCita));
        }
    }

    @Override
    public void cancelarCita(Long idCita) {
        Optional<Citas> resultado = citaRepository.findById(idCita);
        if (resultado.isPresent()) {
            citaRepository.delete(resultado.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La cita con el $id no existe", idCita));
        }
    }

}
