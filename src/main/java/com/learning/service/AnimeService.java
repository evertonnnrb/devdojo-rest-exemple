package com.learning.service;

import com.learning.dto.AnimePostRequestBody;
import com.learning.dto.AnimePutRequestBody;
import com.learning.entities.Anime;
import com.learning.exceptions.BadRequestException;
import com.learning.mapper.AnimeMapper;
import com.learning.repository.AnimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimeService {

    private AnimeRepository animeRepository;

    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Anime getAnimeByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    @Transactional
    public Anime createAnime(AnimePostRequestBody animeDto) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animeDto));
        //return animeRepository.save(Anime.builder().name(animeDto.getName()).build());
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public void deleteAnime(long id) {
        animeRepository.delete(getAnimeByIdOrThrowBadRequestException(id));
    }

    public void replaceAnime(AnimePutRequestBody animePutRequestBody) {
        Anime animeSaved = getAnimeByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = Anime.builder().id(animeSaved.getId())
                .name(animePutRequestBody.getName())
                .build();
        animeRepository.save(anime);
    }


}
