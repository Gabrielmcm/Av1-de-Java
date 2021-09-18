package com.tech4me.musica.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tech4me.musica.model.Musica;
import com.tech4me.musica.repository.MusicaRepository;
import com.tech4me.musica.shared.MusicaDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MusicaServiceImpl implements MusicaService {
    
    @Autowired
    MusicaRepository repositorioMusica;

    @Override
    public List<MusicaDTO> obterTodos() {
        List<Musica> Musicas = repositorioMusica.findAll();
        ModelMapper mapper = new ModelMapper();

        return Musicas.stream()
        .map(musica -> mapper.map(musica, MusicaDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<MusicaDTO> obterPorId(String idMusica) {
        Optional<Musica> optionalMusica = repositorioMusica.findById(idMusica);
        if(optionalMusica.isEmpty()){
            throw new InputMismatchException("Música não encontrada." + idMusica);
        }
        MusicaDTO MusicaDto = new ModelMapper().map(optionalMusica.get(), MusicaDTO.class);

        return Optional.of(MusicaDto);
    }

    @Override
    public MusicaDTO adicionar(MusicaDTO musicaDto) {
        ModelMapper mapper = new ModelMapper();
        Musica musica = mapper.map(musicaDto, Musica.class);
        musica.setId(null);
        musica = repositorioMusica.save(musica);

        return mapper.map(musica, MusicaDTO.class);
    }

    @Override
    public Musica atualizar(String idMusica, MusicaDTO musicaDto){
        if(repositorioMusica.findById(idMusica).isPresent()){
            Musica musicaAtt = repositorioMusica.findById(idMusica).get(); 

            musicaAtt.setTitulo(musicaDto.getTitulo());
            musicaAtt.setArtista(musicaDto.getArtista());
            musicaAtt.setAlbum(musicaDto.getAlbum());
            musicaAtt.setGenero(musicaDto.getGenero());
            musicaAtt.setAnoDoLancamento(musicaDto.getAnoDoLancamento());
            musicaAtt.setCompositor(musicaDto.getCompositor());

            return repositorioMusica.save(musicaAtt);
        }else{
            throw new InputMismatchException("Impossível de Atualizar");
        }
    }

    @Override
    public void delete(String idMusica) {
        repositorioMusica.deleteById(idMusica);
        
    }
}
