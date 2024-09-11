package com.example.banco.Controladores;

import com.example.banco.Entidad.Formulario;
import com.example.banco.Servicios.FormularioServicio;
import com.example.banco.Servicios.PdfServicio;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PdfControlador {

    @Autowired
    private FormularioServicio formularioServicio;

    @Autowired
    private PdfServicio pdfServicio;

    @GetMapping("/generar-pdf")
    public ResponseEntity<Resource> generarPdf(@RequestParam Long idFormulario) {
        try {
            // Verifica si el formulario con el id proporcionado existe
            Formulario formulario = formularioServicio.obtenerPorId(idFormulario);
            if (formulario == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Genera el PDF
            byte[] pdfBytes = pdfServicio.generarPdf(formulario);
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            // Configura los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=formulario_" + idFormulario + ".pdf");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

            // Retorna el PDF como un archivo adjunto
            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException | DocumentException e) {
            // Maneja errores en la generación del PDF
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
