package org.jacp.controller;

import org.jacp.dto.CompetitionDto;
import org.jacp.dto.ParticipantDto;
import org.jacp.dto.QuestionDto;
import org.jacp.dto.SearchDto;
import org.jacp.entity.CompetitionEntity;
import org.jacp.enums.Status;
import org.jacp.mapper.CompetitionMapper;
import org.jacp.service.CompetitionService;
import org.jacp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author saffchen created on 04.12.2023
 */
@RestController
@RequestMapping(value = CreateCompetitionController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CreateCompetitionController {

    static final String URL = "/api/v1/competition";
    @Autowired
    private CompetitionMapper mapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/create")
    public ResponseEntity<CompetitionEntity> createCompetition(@RequestBody SearchDto searchDto) {
        List<QuestionDto> questions = questionService.getQuestionsByFilter(searchDto).getBody();

        Assert.notNull(questions, "Question is null");

        List<Long> participantIDs = searchDto.getParticipantDto().stream()
                .map(ParticipantDto::getId)
                .toList();

        List<Long> questionIDs = questions.stream()
                .map(QuestionDto::getId)
                .toList();

        CompetitionDto competitionDto = mapper.
                toCompetitionDto(questionIDs, participantIDs, searchDto.getDuration(), Status.CREATED.toString());

        CompetitionEntity competitionEntity = mapper.toCompetitionEntity(competitionDto);

        CompetitionEntity createCompetition = competitionService.create(competitionEntity);

        return ResponseEntity.ok(createCompetition);
    }
}
