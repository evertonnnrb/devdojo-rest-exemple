package com.learning.repository;

import com.learning.entities.Anime;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Test for anime repository")
@Log4j2
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    private Anime createAnime() {
        return new Anime().builder().name("Test Anime").build();
    }

    @Test
    @DisplayName("Test persist anime on db test h2")
    void testPersistAnimeInMemoryH2() {
        Anime savedAnime = animeRepository.save(createAnime());
        Assertions.assertNotNull(savedAnime);
        Assertions.assertEquals(savedAnime.getName(), createAnime().getName());
    }

    @Test
    @DisplayName("Test update anime on db test h2")
    void testUpdateAnimeInMemoryH2() {
        Anime savedAnime = animeRepository.save(createAnime());
        savedAnime.setName("_***_(**)");
        Anime updatedAnime = animeRepository.save(savedAnime);
        Assertions.assertNotNull(updatedAnime);
        Assertions.assertEquals(updatedAnime.getName(), "_***_(**)");
        log.info("Anime {}", updatedAnime.getName());
    }

    @Test
    @DisplayName("Test delete anime on db test h2")
    void testDeleteAnimeInMemoryH2() {
        Anime savedAnime = animeRepository.save(createAnime());
        animeRepository.delete(savedAnime);
        Optional<Anime> optionalAnime = animeRepository.findById(savedAnime.getId());
        Assertions.assertTrue(optionalAnime.isEmpty());
    }

    @Test
    @DisplayName("Test find by name anime on db test h2")
    void testFindByNameAnimeInMemoryH2() {
        Anime savedAnime = animeRepository.save(createAnime());
        List<Anime> byName = animeRepository.findByName(savedAnime.getName());
        Assertions.assertTrue(!byName.isEmpty());
        Assertions.assertTrue(byName.contains(savedAnime));
    }
}