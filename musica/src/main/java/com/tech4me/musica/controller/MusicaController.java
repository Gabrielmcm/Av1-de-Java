package com.tech4me.musica.controller;

import java.util.List;
import java.util.Optional;

import com.tech4me.musica.service.MusicaService;
import com.tech4me.musica.shared.MusicaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    
    @Autowired
    MusicaService servicoMusica;

    @GetMapping
    public ResponseEntity<List<MusicaDTO>> obterTodos(){
        List<MusicaDTO> musicas = servicoMusica.obterTodos();

        return new ResponseEntity<>(musicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MusicaDTO>> obterPorId(@PathVariable String id){
        Optional<MusicaDTO> Musica = servicoMusica.obterPorId(id);
        return new ResponseEntity<>(Musica, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MusicaDTO> adicionar(@RequestBody MusicaDTO musicaDto){
    musicaDto = servicoMusica.adicionar(musicaDto);

        return new ResponseEntity<>(musicaDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable String id){
        servicoMusica.detele(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
