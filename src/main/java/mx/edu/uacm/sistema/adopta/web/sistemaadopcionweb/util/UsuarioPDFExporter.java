package mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import mx.edu.uacm.sistema.adopta.web.sistemaadopcionweb.modelo.Mascota;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UsuarioPDFExporter extends AbstractExporter{
    public void export(List<Mascota> listaMascotas, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "application/pdf", ".pdf");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.blue);

        Paragraph paragraph = new Paragraph("Lista de Mascotas", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);


        document.add(paragraph);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, listaMascotas);

        document.add(table);

        document.close();
    }

    private void writeTableData(PdfPTable table, List<Mascota> listaMascotas) {
        for (Mascota mascota : listaMascotas){
            table.addCell(String.valueOf(mascota.getIdMascota()));
            table.addCell(String.valueOf(mascota.getNombreMascota()));
            table.addCell(String.valueOf(mascota.getTipoDeMascota()));
            table.addCell(String.valueOf(mascota.getEdadMascota()));
            table.addCell(String.valueOf(mascota.getImgMascota()));
            table.addCell(String.valueOf(mascota.getPesoMascota()));
            table.addCell(String.valueOf(mascota.getSexoMascota()));
        }
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("Mascota ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nombre Mascota", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Edad", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Foto", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Peso", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sexo", font));
        table.addCell(cell);
    }
}
