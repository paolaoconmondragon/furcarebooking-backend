package com.furcare.furcare.service;

import java.util.List;

import com.furcare.furcare.models.entities.Citas;

public interface ICitasService {

    Citas crearCita(Citas cita);

    Citas editarCita(Long idCita, Citas cita);

    List<Citas> listarCitas();

    Citas finalizarCita(Long idCita);

    void cancelarCita(Long idCita);
}
