package com.example.banco.Servicios;

import com.example.banco.Entidad.Formulario;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfServicio {

    public byte[] generarPdf(Formulario formulario) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.setMargins(20, 20, 30, 30); // Márgenes
        document.open();

        // Fuentes
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font cuerpoFont = new Font(Font.FontFamily.HELVETICA, 12);

        // Título
        document.add(new Paragraph("Formulario de Registro", tituloFont));
        document.add(Chunk.NEWLINE); // Añadir espacio

        // Tabla para la información
        PdfPTable table = new PdfPTable(2); // 2 columnas
        table.setWidthPercentage(100); // Ancho de la tabla al 100%

        table.addCell(new PdfPCell(new Phrase("Nombre:", cuerpoFont)));
        table.addCell(new PdfPCell(new Phrase(formulario.getNombre(), cuerpoFont)));

        table.addCell(new PdfPCell(new Phrase("Apellido:", cuerpoFont)));
        table.addCell(new PdfPCell(new Phrase(formulario.getApellido(), cuerpoFont)));

        table.addCell(new PdfPCell(new Phrase("Cédula:", cuerpoFont)));
        table.addCell(new PdfPCell(new Phrase(formulario.getCedula(), cuerpoFont)));

        table.addCell(new PdfPCell(new Phrase("Número de Cuenta:", cuerpoFont)));
        table.addCell(new PdfPCell(new Phrase(formulario.getNumeroCuenta(), cuerpoFont)));

        table.addCell(new PdfPCell(new Phrase("Correo Electrónico:", cuerpoFont)));
        table.addCell(new PdfPCell(new Phrase(formulario.getCorreoElectronico(), cuerpoFont)));

        document.add(table);



        document.close();
        return outputStream.toByteArray();
    }
}
