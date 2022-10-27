package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

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

    private Date fechaNcimientoUsuario;
    private String generoUsuario;
    @Column(nullable = false, unique = true)
    private String emailUsuario;
    private int edadUsuario;
    private String calleUsuario;
    private int codigoPostalUsuario;
    private String alcaldia;
    private String colonia;
    private int numeroExterior;
    private int numeroInterior;
    private int celUsuario;
    private int telFijoUsuario;
    private File comprobanteDomicilioFile;
    private File identificacionOficialFile;
    @Column(nullable = false, length = 64)
    private String password;

    public Usuario(Role role, String nombreUsuario, String apellidoPaterno, String apellidoMaterno, Date fechaNcimientoUsuario, String generoUsuario, String emailUsuario, int edadUsuario, String calleUsuario, int codigoPostalUsuario, String alcaldia, String colonia, int numeroExterior, int numeroInterior, int celUsuario, int telFijoUsuario, File comprobanteDomicilioFile, File identificacionOficialFile, String password) {
        this.role = role;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNcimientoUsuario = fechaNcimientoUsuario;
        this.generoUsuario = generoUsuario;
        this.emailUsuario = emailUsuario;
        this.edadUsuario = edadUsuario;
        this.calleUsuario = calleUsuario;
        this.codigoPostalUsuario = codigoPostalUsuario;
        this.alcaldia = alcaldia;
        this.colonia = colonia;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.celUsuario = celUsuario;
        this.telFijoUsuario = telFijoUsuario;
        this.comprobanteDomicilioFile = comprobanteDomicilioFile;
        this.identificacionOficialFile = identificacionOficialFile;
        this.password = password;
    }

    public Usuario() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public Date getFechaNcimientoUsuario() {
        return fechaNcimientoUsuario;
    }

    public void setFechaNcimientoUsuario(Date fechaNcimientoUsuario) {
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

    public int getCodigoPostalUsuario() {
        return codigoPostalUsuario;
    }

    public void setCodigoPostalUsuario(int codigoPostalUsuario) {
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

    public int getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(int numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public int getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(int numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public int getCelUsuario() {
        return celUsuario;
    }

    public void setCelUsuario(int celUsuario) {
        this.celUsuario = celUsuario;
    }

    public int getTelFijoUsuario() {
        return telFijoUsuario;
    }

    public void setTelFijoUsuario(int telFijoUsuario) {
        this.telFijoUsuario = telFijoUsuario;
    }

    public File getComprobanteDomicilioFile() {
        return comprobanteDomicilioFile;
    }

    public void setComprobanteDomicilioFile(File comprobanteDomicilioFile) {
        this.comprobanteDomicilioFile = comprobanteDomicilioFile;
    }

    public File getIdentificacionOficialFile() {
        return identificacionOficialFile;
    }

    public void setIdentificacionOficialFile(File identificacionOficialFile) {
        this.identificacionOficialFile = identificacionOficialFile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
