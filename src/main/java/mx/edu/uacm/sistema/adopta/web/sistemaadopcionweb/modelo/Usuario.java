package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false, length = 20)
    private String nombreUsuario;
    private String apellidoPaterno;
    private String apellidoMaterno;

    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaNcimientoUsuario;
    private String generoUsuario;
    @Column(nullable = false, unique = true)
    private String emailUsuario;
    private int edadUsuario;
    private String calleUsuario;
    private Long codigoPostalUsuario;
    private String alcaldia;
    private String colonia;
    private Long numeroExterior;
    private Long numeroInterior;
    private Long celUsuario;
    private Long telFijoUsuario;
    private String comprobanteDomicilioFile;
    private String identificacionOficialFile;
    @Column(nullable = false, length = 64)
    private String password;
    private boolean habilitado;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Mascota> mascotas = new HashSet<>();





    public Usuario() {
    }



    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public LocalDate getFechaNcimientoUsuario() {
        return fechaNcimientoUsuario;
    }

    public void setFechaNcimientoUsuario(LocalDate fechaNcimientoUsuario) {
        this.fechaNcimientoUsuario = fechaNcimientoUsuario;
    }

    public String getGeneroUsuario() {
        return generoUsuario;
    }

    public void setGeneroUsuario(String generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public int getEdadUsuario() {
        return edadUsuario;
    }

    public void setEdadUsuario(int edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

    public String getCalleUsuario() {
        return calleUsuario;
    }

    public void setCalleUsuario(String calleUsuario) {
        this.calleUsuario = calleUsuario;
    }

    public Long getCodigoPostalUsuario() {
        return codigoPostalUsuario;
    }

    public void setCodigoPostalUsuario(Long codigoPostalUsuario) {
        this.codigoPostalUsuario = codigoPostalUsuario;
    }

    public String getAlcaldia() {
        return alcaldia;
    }

    public void setAlcaldia(String alcaldia) {
        this.alcaldia = alcaldia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public Long getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(Long numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public Long getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(Long numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public Long getCelUsuario() {
        return celUsuario;
    }

    public void setCelUsuario(Long celUsuario) {
        this.celUsuario = celUsuario;
    }

    public Long getTelFijoUsuario() {
        return telFijoUsuario;
    }

    public void setTelFijoUsuario(Long telFijoUsuario) {
        this.telFijoUsuario = telFijoUsuario;
    }

    public String getComprobanteDomicilioFile() {
        return comprobanteDomicilioFile;
    }

    public void setComprobanteDomicilioFile(String comprobanteDomicilioFile) {
        this.comprobanteDomicilioFile = comprobanteDomicilioFile;
    }

    public String getIdentificacionOficialFile() {
        return identificacionOficialFile;
    }

    public void setIdentificacionOficialFile(String identificacionOficialFile) {
        this.identificacionOficialFile = identificacionOficialFile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Set<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(Set<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }

    @Transient
    public String getIneImagePath(){
        if (idUsuario == null || identificacionOficialFile == null) return "/image/default-user.png";
        return "/user-photos/" + this.idUsuario + "/" + this.identificacionOficialFile;
    }

    public void addMascotas(Mascota mascota){
        this.mascotas.add(mascota);

    }

}


