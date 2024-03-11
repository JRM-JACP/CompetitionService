package org.jacp.competitionservice;

import org.jacp.controller.CompetitionController;
import org.jacp.dto.FilterDto;
import org.jacp.dto.ParticipantDto;
import org.jacp.dto.SearchDto;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Tags;
import org.jacp.mapper.CompetitionMapper;
import org.jacp.service.CompetitionService;
import org.jacp.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CompetitionControllerTest {
    @Mock
    private CompetitionMapper mapper;
    @Mock
    private QuestionService questionService;
    @Mock
    private CompetitionService competitionService;
    @InjectMocks
    private CompetitionController competitionController;

    @Test
    public void createCompetition_Success(){
        //given
        var filterDto = new FilterDto();
        filterDto.setLimitTasks(3);
        filterDto.setDifficulty(Difficulty.EASY);
        filterDto.setTagsList(List.of(Tags.TREE, Tags.ARRAYS));

        var searchDto = new SearchDto();
        searchDto.setFilterDto(filterDto);
        searchDto.setParticipantDto(List.of(new ParticipantDto()));
        searchDto.setDuration(10);

        //when

        //then
    }
}
