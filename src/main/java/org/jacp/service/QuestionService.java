package org.jacp.service;

import lombok.RequiredArgsConstructor;
import org.jacp.dto.FilterDto;
import org.jacp.dto.QuestionDto;
import org.jacp.repositry.CompetitionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author saffchen created on 13.12.2023
 */
@Component
@RequiredArgsConstructor
public class QuestionService {

    private final CompetitionRepository competitionRepository;

    private final RestTemplate restTemplate;
    @Value("${url.questionService.baseUrl}")
    private String baseUrl;

    public List<QuestionDto> getQuestionsByFilter(FilterDto filterDto) {
        ResponseEntity<List<QuestionDto>> response = restTemplate.exchange(
                URI.create(baseUrl + "/filter"),
                HttpMethod.POST,
                new HttpEntity<>(filterDto),
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public List<QuestionDto> getTasksFromCompetition(Long competitionId) {
        List<Long> ids = competitionRepository.getCompetitionEntityById(competitionId).getTasks();
        ResponseEntity<List<QuestionDto>> response = restTemplate.exchange(
                URI.create(baseUrl + "?ids=" + ids.stream().map(Object::toString).collect(Collectors.joining(","))),
                HttpMethod.GET,
                new HttpEntity<>(ids),
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
}