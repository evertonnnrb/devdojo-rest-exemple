package com.learning.client;

import com.learning.entities.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        String url = "http://localhost:8080/animes/all";

        //    ResponseEntity<Anime> animeResponseEntity =
        //          new RestTemplate().getForEntity(url+="/2", Anime.class);
        // log.info(animeResponseEntity);

        //Anime object = new RestTemplate().
        //      getForObject(url+="/{id}", Anime.class,3);
        //log.info(object);

        //String s = new RestTemplate().getForObject("https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados?formato=csv&dataInicial=01/01/2023&dataFinal=30/06/2023", String.class);
        //log.info(s);

        //Anime[] animes = new RestTemplate().getForObject(
         //       url += "/all", Anime[].class);
        //log.info(Arrays.toString(animes));

        //@formatter:off
//        ResponseEntity<Object> exchange = new RestTemplate().exchange(url + "/all", HttpMethod.GET, null,
  //              new ParameterizedTypeReference<Object>() {});
        //@formatter:on
    //    log.info(exchange.getBody());

        Anime king = Anime.builder().name("KingDon").build();
        //Anime kingSaved = new RestTemplate().postForObject(url,king,Anime.class);
        ResponseEntity<Anime> kingSaved = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(king), Anime.class);
        log.info("saved anime {}", kingSaved);
    }
}
