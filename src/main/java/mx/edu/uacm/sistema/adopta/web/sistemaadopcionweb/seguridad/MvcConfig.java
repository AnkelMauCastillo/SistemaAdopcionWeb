package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.seguridad;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "user-photos";
        Path usuarioFotosDir = Paths.get(dirName);

        String usuarioFotosPath = usuarioFotosDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:/" + usuarioFotosPath + "/");

        String dirNameMascotas = "mascotas-photos";
        Path mascotaFotosDir = Paths.get(dirNameMascotas);

        String mascotaFotosPath = mascotaFotosDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + dirNameMascotas + "/**")
                .addResourceLocations("file:/" + mascotaFotosPath + "/");


    }

}
