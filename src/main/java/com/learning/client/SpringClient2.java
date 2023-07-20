package com.learning.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SpringClient2 {
    public static void main(String[] args) {
        // URL base da API da taxa Selic
        String baseUrl = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados";

        // Data atual
        LocalDate currentDate = LocalDate.now();

        // Data de início (30 dias atrás)
        LocalDate startDate = currentDate.minusDays(30);

        // Formatação das datas para o formato esperado pela API (AAAA-MM-DD)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDateStr = startDate.format(formatter);
        String currentDateStr = currentDate.format(formatter);

        // Montando a URL completa com os parâmetros de data
        String url = baseUrl + "?formato=json&dataInicial=" + startDateStr + "&dataFinal=" + currentDateStr;

        // Criando uma instância do RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Fazendo a requisição GET para a API
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        // Verificando se a requisição foi bem-sucedida (código de status 200)
        if (response.getStatusCode().is2xxSuccessful()) {
            // Processando a resposta JSON
            String responseBody = response.getBody();

            // Aqui você pode manipular o responseBody conforme necessário para extrair os dados da taxa Selic
            // Por exemplo, você pode usar uma biblioteca JSON como o Jackson para desserializar o JSON em objetos Java
            // e extrair as informações desejadas.

            System.out.println(responseBody);
        } else {
            System.out.println("A requisição não foi bem-sucedida. Código de status: " + response.getStatusCodeValue());
        }
    }
}
