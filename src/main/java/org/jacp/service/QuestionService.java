package org.jacp.service;

import lombok.RequiredArgsConstructor;
import org.jacp.dto.FilterDto;
import org.jacp.dto.QuestionDto;
import org.jacp.dto.SearchDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * @author saffchen created on 13.12.2023
 */
@Component
@RequiredArgsConstructor
public class QuestionService {

    private final RestTemplate restTemplate;
    @Value("${url.questionService}")
    private String url;

    public List<QuestionDto> getQuestionsByFilter(FilterDto filterDto) {

        ResponseEntity<List<QuestionDto>> response = restTemplate.exchange(
                URI.create(url),
                HttpMethod.POST,
                new HttpEntity<>(filterDto),
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }
}