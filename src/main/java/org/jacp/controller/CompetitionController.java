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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author saffchen created on 04.12.2023
 */
@RestController
@RequestMapping(value = CompetitionController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CompetitionController {

    static final String URL = "/api/v1/competition";
    @Autowired
    private CompetitionMapper mapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/create")
    public ResponseEntity<CompetitionEntity> createCompetition(@RequestBody SearchDto searchDto) {
        List<QuestionDto> questions = questionService.getQuestionsByFilter(searchDto.getFilterDto());
        List<ParticipantDto> participants = searchDto.getParticipantDto();

        Assert.notNull(questions, "Question are null");

        List<Long> participantIDs = participants.stream()
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

    @GetMapping
    public ResponseEntity<List<CompetitionDto>> getAllByStatusCompetition(@RequestParam("status") Status status) {
        return ResponseEntity.ok(mapper.toCompetitionDtoList(competitionService.getAllByStatus(status)));
    }
}
