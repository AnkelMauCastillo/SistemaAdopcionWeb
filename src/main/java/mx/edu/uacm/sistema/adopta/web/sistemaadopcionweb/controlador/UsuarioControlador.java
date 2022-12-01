package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.controlador;

import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Mascota;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Role;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Usuario;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.MascotaRepository;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.repositorio.UsuarioRepository;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.seguridad.CustomUserDetails;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.service.MascotaService;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.service.UserNotFoundException;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.service.UsuarioService;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.util.FileUploadUtil;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.util.UsuarioPDFExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioControlador {

    @Autowired
    private UsuarioRepository userRepo;

    //se agrego repo mascotas
    @Autowired
    private MascotaRepository mascotaRepo;

    @Autowired
    private MascotaService mascotaService;

    @Autowired
    private UsuarioService service;

    @GetMapping("")
    public String paginaDeInicio(){
        return "index";
    }


    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model){
        List<String> options = new ArrayList<>();
        model.addAttribute("usuario", new Usuario());

        agregarRoles(options);
        model.addAttribute("options", options);

        return "signup_form";

    }

    private static void agregarRoles(List<String> options) {
        for(Role role: Role.values()){
            if (!role.toString().equals("ADMIN")) {
                options.add(role.toString());
            }
        }
    }

    @PostMapping("/registro_de_proceso")
    public String processRegister(Usuario user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return "register_success";
    }

    //controlador alta mascotas
    @PostMapping("/donador/donador_registro_alta")
    public String procesoAltaMascota(@AuthenticationPrincipal CustomUserDetails loggedUser,Mascota mascota, @RequestParam("image") MultipartFile multipartFile)throws IOException  {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            mascota.setImgMascota(fileName);
            String email = loggedUser.getUsername();
            Usuario usuario = userRepo.getUsuarioByEmailUsuario(email);
            mascota.addUsuario(usuario);
            Mascota saveMascota = mascotaRepo.save(mascota);
            String uploadDir = "mascotas-photos/" + saveMascota.getIdMascota();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        }else {
            if (mascota.getImgMascota().isEmpty()) mascota.setImgMascota(null);
            mascotaRepo.save(mascota);
        }


        return "redirect:/donador/home";
    }

    @GetMapping("/donador/login")
    public String verPaginaDonadorLogin(){
        return "donador/donador_login";
    }

    @GetMapping("/candidato/login")
    public String verPaginaCandidatoLogin(){
        return "candidato/candidato_login";
    }

    @GetMapping("/donador/home")
    public String verPaginaDonadorHome(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        String email = loggedUser.getUsername();
        Usuario usuario = userRepo.getUsuarioByEmailUsuario(email);
        String listRoles = usuario.getRole().toString();
        String id = String.valueOf(usuario.getIdUsuario());
        System.out.println(usuario);
        model.addAttribute("usuario",usuario);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("id", id);


        return "donador/donador_home";

    }

    @GetMapping("/candidato/home")
    public String verPaginaCandidatoHome(@AuthenticationPrincipal CustomUserDetails loggedUser, Model model){
        String email = loggedUser.getUsername();
        Usuario usuario = userRepo.getUsuarioByEmailUsuario(email);
        String listRoles = usuario.getRole().toString();
        String id = String.valueOf(usuario.getIdUsuario());
        System.out.println(usuario);
        model.addAttribute("usuario",usuario);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("id", id);
        return "candidato/candidato_home";
    }

    @GetMapping("/donador/registro_mascotas/{idUsuario}")
    public String mostrarFormularioDeRegistroMascotas(@PathVariable() Long idUsuario, Model model, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuarioId = service.get(idUsuario);
            Mascota mascota = new Mascota();
            mascota.addUsuario(usuarioId);
            System.out.println(mascota);
            String nombreCompleto = usuarioId.getNombreUsuario() + " " + usuarioId.getApellidoPaterno() + " " + usuarioId.getApellidoMaterno();
            List<String> sexoMascotas = new ArrayList<>();
            List<String> tipoMascotas = new ArrayList<>();
            sexoMascotas.add("Macho");
            sexoMascotas.add("Hembra");
            tipoMascotas.add("Perro");
            tipoMascotas.add("Gato");
            model.addAttribute("tipoMascotas", tipoMascotas);
            model.addAttribute("sexoMascotas", sexoMascotas);
            model.addAttribute("mascota", mascota);
            model.addAttribute("usuarioId", usuarioId);
            model.addAttribute("nombreCompleto", nombreCompleto);
            return "donador/donador_agregar_mascotas";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("mensaje", ex.getMessage());
            return "redirect:/donador/home";
        }


    }


    @GetMapping("/donador/editar_usuario/{idUsuario}")
    public String editarUsuario(@PathVariable(name = "idUsuario") Long idUsuario,Model model ,RedirectAttributes redirectAttributes){
        try {
            Usuario usuario = service.get(idUsuario);
            usuario.setHabilitado(true);
            String listRoles = usuario.getRole().toString();
            model.addAttribute("usuario",usuario);
            model.addAttribute("listaRoles", listRoles);
            model.addAttribute("pageTitle", "Editar Usuario (ID: " + idUsuario + ")");
            return "donador/donador_edit_form";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("mensaje", ex.getMessage());
            return "redirect:/donador/home";
        }

    }

    @GetMapping("/candidato/editar_usuario/{idUsuario}")
    public String editarUsuarioCandidato(@PathVariable(name = "idUsuario") Long idUsuario,Model model ,RedirectAttributes redirectAttributes){
        try {
            Usuario usuario = service.get(idUsuario);
            usuario.setHabilitado(true);
            String listRoles = usuario.getRole().toString();
            model.addAttribute("usuario",usuario);
            model.addAttribute("listaRoles", listRoles);
            model.addAttribute("pageTitle", "Editar Usuario (ID: " + idUsuario + ")");
            return "candidato/candidato_edit_form";
        } catch (UserNotFoundException ex) {
            redirectAttributes.addFlashAttribute("mensaje", ex.getMessage());
            return "redirect:/candidato/home";
        }

    }

    @GetMapping("/donador/listado_mascotas/{idUsuario}")
    public String listadMascotas(@PathVariable(name = "idUsuario") Long idUsuario,Model model ,RedirectAttributes redirectAttributes){
        try {
            Usuario usuario = service.get(idUsuario);
            List<Mascota> mascotas = mascotaService.listAll();
            List<Mascota> auxMascotas = new ArrayList<>();
            System.out.println(usuario.getMascotas());
            for(Mascota mascota : mascotas){
                //System.out.println("Ingresaste al for" + mascota.getUsuarios().contains(usuario));

                if (mascota.getUsuarios().contains(usuario)) {
                    System.out.println(mascota.getNombreMascota());
                    auxMascotas.add(mascota);
                }
            }

            //System.out.println("Prueba mascotas" + mascotas);

            model.addAttribute("mascotas",auxMascotas);
            model.addAttribute("usuario", usuario);
            return  "donador/listado_mascotas";
        } catch (UserNotFoundException e) {
            return "redirect:/donador/home";
        }

    }

    @GetMapping("/candidato/listado_mascotas/{idUsuario}")
    public String listadMascotasAdoptadas(@PathVariable(name = "idUsuario") Long idUsuario,Model model ,RedirectAttributes redirectAttributes){
        try {
            Usuario usuario = service.get(idUsuario);
            List<Mascota> mascotas = mascotaService.listAll();
            List<Mascota> auxMascotas = new ArrayList<>();
            System.out.println(usuario.getMascotas());
            for(Mascota mascota : mascotas){
                //System.out.println("Ingresaste al for" + mascota.getUsuarios().contains(usuario));

                if (mascota.getUsuarios().contains(usuario)) {
                    System.out.println(mascota.getNombreMascota());
                    auxMascotas.add(mascota);
                }
            }

            //System.out.println("Prueba mascotas" + mascotas);

            model.addAttribute("mascotas",auxMascotas);
            model.addAttribute("usuario", usuario);
            return  "candidato/listado_adoptar";
        } catch (UserNotFoundException e) {
            return "redirect:/candidato/home";
        }

    }

    @GetMapping("/candidato/adoptar/{idUsuario}")
    public String adoptarCandidato(@AuthenticationPrincipal CustomUserDetails loggedUser, @PathVariable(name = "idUsuario") Long idUsuario,Model model ,RedirectAttributes redirectAttributes){
        Mascota mascota = mascotaRepo.findByIdMascota(idUsuario);
        String email = loggedUser.getUsername();
        Usuario usuario = userRepo.getUsuarioByEmailUsuario(email);
        usuario.addMascotas(mascota);
        Usuario usuarioSaved =  userRepo.save(usuario);
        mascota.addUsuario(usuarioSaved);
        mascotaRepo.save(mascota);

        return "candidato/adoptar";
    }

    @GetMapping("/candidato/adoptar_mascotas/{idUsuario}")
    public String listadMascotasCandidato(@PathVariable(name = "idUsuario") Long idUsuario,Model model ,RedirectAttributes redirectAttributes){
        try {
            Usuario usuario = service.get(idUsuario);
            List<Mascota> mascotas = mascotaService.listAll();
            //List<Mascota> auxMascotas = new ArrayList<>();
            System.out.println(usuario.getMascotas());


            //System.out.println("Prueba mascotas" + mascotas);

            model.addAttribute("mascotas",mascotas);
            model.addAttribute("usuario", usuario);
            return  "candidato/listado_mascotas";
        } catch (UserNotFoundException e) {
            return "redirect:/candidato/home";
        }

    }

    @PostMapping("/donador/guardar")
    public String guardarUsuario(Usuario usuario, RedirectAttributes redirectAttributes,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            LocalDate usuarioFechaNacimiento= usuario.getFechaNcimientoUsuario();
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            usuario.setIdentificacionOficialFile(fileName);
            usuario.setEdadUsuario(Period.between(usuarioFechaNacimiento, LocalDate.now()).getYears());
            Usuario savedUser = service.guardar(usuario);
            String uploadDir = "user-photos/" + savedUser.getIdUsuario();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } else {
            if (usuario.getIdentificacionOficialFile().isEmpty()) usuario.setIdentificacionOficialFile(null);
            service.guardar(usuario);
        }
        System.out.println(usuario);
        System.out.println(multipartFile.getOriginalFilename());



        redirectAttributes.addFlashAttribute("mensaje", "El usuario se ha guardado con éxito.");
        return "redirect:/donador/home";
    }

    @PostMapping("/candidato/guardar")
    public String guardarUsuarioCandidato(Usuario usuario, RedirectAttributes redirectAttributes,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            LocalDate usuarioFechaNacimiento= usuario.getFechaNcimientoUsuario();
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            usuario.setIdentificacionOficialFile(fileName);
            usuario.setEdadUsuario(Period.between(usuarioFechaNacimiento, LocalDate.now()).getYears());
            Usuario savedUser = service.guardar(usuario);
            String uploadDir = "user-photos/" + savedUser.getIdUsuario();

            FileUploadUtil.cleanDir(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        } else {
            if (usuario.getIdentificacionOficialFile().isEmpty()) usuario.setIdentificacionOficialFile(null);
            service.guardar(usuario);
        }
        System.out.println(usuario);
        System.out.println(multipartFile.getOriginalFilename());



        redirectAttributes.addFlashAttribute("mensaje", "El usuario se ha guardado con éxito.");
        return "redirect:/candidato/home";
    }

    @GetMapping("/candidato/export/pdf")
    public void exportPDF(HttpServletResponse response, @AuthenticationPrincipal CustomUserDetails loggedUser) throws IOException{
        String email = loggedUser.getUsername();
        Usuario usuario = userRepo.getUsuarioByEmailUsuario(email);
        List<Mascota> listaMascotas = mascotaService.listAll();

        List<Mascota> auxMascotas = new ArrayList<>();
        System.out.println(usuario.getMascotas());
        for(Mascota mascota : listaMascotas){
            //System.out.println("Ingresaste al for" + mascota.getUsuarios().contains(usuario));

            if (mascota.getUsuarios().contains(usuario)) {
                System.out.println(mascota.getNombreMascota());
                auxMascotas.add(mascota);
            }
        }

        UsuarioPDFExporter exporter = new UsuarioPDFExporter();
        exporter.export(auxMascotas, response);

    }




}
