package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;
    private String nombreMascota;
    private  String sexoMascota;
    private Long edadMascota;
    private Double pesoMascota;

    private String imgMascota;

    private String tipoDeMascota;

    @ManyToMany
    @JoinTable(
            name = "mascotas_usuarios",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios = new HashSet<>();





    public Mascota() {

    }

    public Mascota(String nombreMascota, String sexoMascota, Long edadMascota, Double pesoMascota) {
        this.nombreMascota = nombreMascota;
        this.sexoMascota = sexoMascota;
        this.edadMascota = edadMascota;
        this.pesoMascota = pesoMascota;
    }

    public String getTipoDeMascota() {
        return tipoDeMascota;
    }

    public void setTipoDeMascota(String tipoDeMascota) {
        this.tipoDeMascota = tipoDeMascota;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getSexoMascota() {
        return sexoMascota;
    }

    public void setSexoMascota(String sexoMascota) {
        this.sexoMascota = sexoMascota;
    }

    public Long getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(Long edadMascota) {
        this.edadMascota = edadMascota;
    }

    public Double getPesoMascota() {
        return pesoMascota;
    }

    public void setPesoMascota(Double pesoMascota) {
        this.pesoMascota = pesoMascota;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public void addUsuario(Usuario usuario){
        this.usuarios.add(usuario);
    }

    public String getImgMascota() {
        return imgMascota;
    }

    public void setImgMascota(String imgMascota) {
        this.imgMascota = imgMascota;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "nombreMascota='" + nombreMascota + '\'' +
                '}';
    }

    @Transient
    public String getMascotaImagePath(){
        if (idMascota == null || imgMascota == null) return "/image/default-user.png";
        return "/mascotas-photos/" + this.idMascota +  "/" + this.imgMascota;
    }
}

