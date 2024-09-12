package com.example.banco.Servicios;

import com.example.banco.Entidad.Formulario;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfServicio {

    public byte[] generarPdf(Formulario formulario) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.setMargins(36, 36, 50, 50);

        document.open();

        // Fuentes
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
        Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
        Font cuerpoFont = new Font(Font.FontFamily.HELVETICA, 12);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);

        // Logo del banco
        Image logo = Image.getInstance("src/main/resources/static/imagenes/logo.png");
        logo.scaleToFit(100, 50);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Título principal
        Paragraph titulo = new Paragraph("Formulario de Registro - Banco BBVA", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(Chunk.NEWLINE);

        // Subtítulo
        Paragraph subtitulo = new Paragraph("Datos del Cliente", subtituloFont);
        subtitulo.setAlignment(Element.ALIGN_LEFT);
        document.add(subtitulo);
        document.add(Chunk.NEWLINE);

        // Tabla de información
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        float[] columnWidths = {1f, 2f};
        table.setWidths(columnWidths);

        // Encabezados de la tabla
        BaseColor headerColor = new BaseColor(0, 102, 204);
        PdfPCell headerCell;

        headerCell = new PdfPCell(new Phrase("CAMPOS", headerFont));
        headerCell.setBackgroundColor(headerColor);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(5);
        headerCell.setBorderWidth(2);
        table.addCell(headerCell);

        headerCell = new PdfPCell(new Phrase("DATOS", headerFont));
        headerCell.setBackgroundColor(headerColor);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerCell.setPadding(5);
        headerCell.setBorderWidth(2);
        table.addCell(headerCell);

        // Datos del formulario
        table.addCell(crearCelda("Nombre:", cuerpoFont));
        table.addCell(crearCelda(formulario.getNombre(), cuerpoFont));

        table.addCell(crearCelda("Apellido:", cuerpoFont));
        table.addCell(crearCelda(formulario.getApellido(), cuerpoFont));

        table.addCell(crearCelda("Cédula:", cuerpoFont));
        table.addCell(crearCelda(formulario.getCedula(), cuerpoFont));

        table.addCell(crearCelda("Número de Cuenta:", cuerpoFont));
        table.addCell(crearCelda(formulario.getNumeroCuenta(), cuerpoFont));

        table.addCell(crearCelda("Correo Electrónico:", cuerpoFont));
        table.addCell(crearCelda(formulario.getCorreoElectronico(), cuerpoFont));

        document.add(table);


        Paragraph footer = new Paragraph("Banco BBVA - Dirección: Av. Principal 123, Ciudad - Teléfono: 123-456-7890", cuerpoFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(30f);
        document.add(footer);

        document.close();
        writer.close();
        return outputStream.toByteArray();
    }

    private PdfPCell crearCelda(String texto, Font fuente) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, fuente));
        cell.setPadding(5);
        cell.setBorderWidth(1);
        return cell;
    }
}
