package org.jacp.controller;

import org.jacp.entity.QuestionEntity;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Tags;
import org.jacp.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author saffchen created on 04.12.2023
 */
@RestController
@RequestMapping(value = CreateCompetitionController.URL)
public class CreateCompetitionController {

    static final String URL = "/api/v1/create-competition";

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("/")
    public ResponseEntity<QuestionEntity> createCompetition(@PathVariable Difficulty difficulty,
                                                            @PathVariable List<Tags> tags) {
        return competitionService.responseFromQuestionService(difficulty, tags);
    }
}
