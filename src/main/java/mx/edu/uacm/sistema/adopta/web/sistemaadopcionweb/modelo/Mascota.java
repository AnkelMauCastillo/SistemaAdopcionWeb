package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Mascota {
    private Long idMascota;
    private String nombreMascota;
    private  String sexoMascota;
    private Long edadMascota;
    private Double pesoMascota;


}
