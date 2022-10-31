package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Mascota;
import org.springframework.data.repository.CrudRepository;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {
}
//creacion repo mascotas