package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Mascota;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.util.ArrayList;
/*
@Service
public class CreatePdfService {

    public void createPdf(){
        String filePdf = "D:/Desktop/pdfFile/SamplePdfFile.pdf";
        ArrayList<Mascota> mascotaList = new ArrayList<>();
        //Mascota mascota1 = new Mascota(1L,"Peluche","Macho",3L,3.45);
        //Mascota mascota2 = new Mascota(1L,"Dexter","Macho",3L,3.45);
        //mascotaList.add(mascota1);
        //mascotaList.add(mascota2);


        try {
            PdfWriter writer = new PdfWriter(filePdf);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            Paragraph p1 = new Paragraph("Hola bienvenido a mi plantilla");
            float[] columnWidth = {200f,200f,200f,200f,200f};
            Table table = new Table(columnWidth);
            table.addCell(new Cell().add("ID"));
            table.addCell(new Cell().add("Nombre"));
            table.addCell(new Cell().add("Sexo"));
            table.addCell(new Cell().add("Edad"));
            table.addCell(new Cell().add("Peso"));
            for (Mascota dataMascotas : mascotaList) {
                table.addCell(new Cell().add(String.valueOf(dataMascotas.getIdMascota())));
                table.addCell(new Cell().add(String.valueOf(dataMascotas.getNombreMascota())));
                table.addCell(new Cell().add(String.valueOf(dataMascotas.getSexoMascota())));
                table.addCell(new Cell().add(String.valueOf(dataMascotas.getEdadMascota())));
                table.addCell(new Cell().add(String.valueOf(dataMascotas.getPesoMascota())));
            }
            document.add(p1);
            document.add(table);
            document.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
*
 */
