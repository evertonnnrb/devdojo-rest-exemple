package com.learning.controller;

import com.learning.dto.AnimePostRequestBody;
import com.learning.dto.AnimePutRequestBody;
import com.learning.entities.Anime;
import com.learning.repository.AnimeRepository;
import com.learning.service.AnimeService;
import com.learning.util.LocalDateTimeUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/animes")
@RequiredArgsConstructor
@Log4j2
public class AnimeController {
    private final AnimeRepository animeRepository;
    private final LocalDateTimeUtil localDateTimeUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<Anime>> getAnimes(Pageable pageable) {
        log.info(localDateTimeUtil.formatLocaDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Anime>> getAnimes() {
        return ResponseEntity.ok(animeService.getAllAnimes());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable long id) {
        return ResponseEntity.ok(animeService.getAnimeByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeRepository.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> createAnime(@RequestBody @Valid AnimePostRequestBody anime) {
        return new ResponseEntity<>(animeService.createAnime(anime), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable long id) {
        animeService.deleteAnime(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replaceAnime(@RequestBody @Valid AnimePutRequestBody animePutRequestBody) {
        animeService.replaceAnime(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
