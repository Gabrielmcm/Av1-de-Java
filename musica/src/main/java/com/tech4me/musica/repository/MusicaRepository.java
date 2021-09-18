package com.tech4me.musica.repository;

import com.tech4me.musica.model.Musica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends MongoRepository<Musica, String> {
    
}
