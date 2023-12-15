package com.example.exemplos3.controller;

import com.example.exemplos3.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.io.IOException;

@RestController
public class AwsController {

    @Autowired
    private AwsService service;

    @PostMapping
    public ResponseEntity<?> enviar(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(service.enviar(file));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> buscar(@PathVariable String nome) {
        try {
            return ResponseEntity.ok(service.buscar(nome));
        } catch (NoSuchKeyException | IOException e) {
          return  ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<?> excluir(@PathVariable String nome) {
        service.excluir(nome);
        return ResponseEntity.ok().build();
    }


}
