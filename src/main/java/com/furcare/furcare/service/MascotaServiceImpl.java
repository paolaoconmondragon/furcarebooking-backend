package com.furcare.furcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.furcare.furcare.models.entities.Mascota;
import com.furcare.furcare.repositories.MascotaRepository;

@Service
public class MascotaServiceImpl  implements IMascotaService{

    @Autowired
    public MascotaRepository mascotaRepository;

    @Override
    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    

}
