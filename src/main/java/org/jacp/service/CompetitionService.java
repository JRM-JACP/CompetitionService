package org.jacp.service;

import org.jacp.dto.FilterQuestionDto;
import org.jacp.entity.CompetitionEntity;
import org.jacp.entity.QuestionEntity;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Tags;
import org.jacp.repositry.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author saffchen created on 04.12.2023
 */
@Component
public class CompetitionService {

    @Value("${url.questionService}")
    private String url;

    @Autowired
    private CompetitionRepository competitionRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public CompetitionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CompetitionEntity save(CompetitionEntity competitionEntity) {
        return competitionRepository.save(competitionEntity);
    }

    public ResponseEntity<QuestionEntity> responseFromQuestionService(Difficulty difficulty, List<Tags> tags) {
        FilterQuestionDto filterQuestionDto = new FilterQuestionDto(difficulty, tags);
        return restTemplate.getForEntity(url, QuestionEntity.class, filterQuestionDto);
    }
}
