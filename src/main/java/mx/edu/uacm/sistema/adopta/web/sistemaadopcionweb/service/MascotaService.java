package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.service;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Mascota;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> listAll(){
        return (List<Mascota>) mascotaRepository.findAll();
    }
}
