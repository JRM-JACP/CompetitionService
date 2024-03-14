package org.jacp.competitionservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.jacp.dto.*;
import org.jacp.entity.CompetitionEntity;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Status;
import org.jacp.enums.Tags;
import org.jacp.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class CompetitionControllerIT {
    @MockBean
    private QuestionService questionService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withExposedPorts(5432)
            .withCreateContainerCmdModifier(cmd -> cmd.withHostConfig(
                    new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(5434), new ExposedPort(5432)))))
            .withDatabaseName("competition")
            .withUsername("postgres")
            .withPassword("postgres")
            .withEnv("COMPETITION_DB_USER", "docker")
            .withEnv("COMPETITION_DB_PASSWORD", "docker")
            .withEnv("COMPETITION_DB_NAME", "competition")
            .withCopyFileToContainer(MountableFile.forHostPath("db/init/init-script.sql"), "/docker-entrypoint-initdb.d/init-script.sql");

    @Test
    public void createCompetition_ReturnsCompetitionEntity() throws Exception {
        // given
        var filterDto = new FilterDto();
        filterDto.setLimitTasks(3);
        filterDto.setDifficulty(Difficulty.EASY);
        filterDto.setTagsList(List.of(Tags.TREE, Tags.ARRAYS));

        var participantDto = new ParticipantDto();
        participantDto.setId(1);

        var searchDto = new SearchDto();
        searchDto.setFilterDto(filterDto);
        searchDto.setParticipantDto(List.of(participantDto));
        searchDto.setDuration(10);

        var questionDto = new QuestionDto();
        questionDto.setId(1L);

        String requestBody = objectMapper.writeValueAsString(searchDto);
        when(questionService.getQuestionsByFilter(filterDto)).thenReturn(List.of(questionDto));

        // when
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/competition/create")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        var competitionEntityResponse = objectMapper.readValue(response, CompetitionEntity.class);

        // then
        assertEquals(searchDto.getDuration(), competitionEntityResponse.getDuration());
    }

    @Test
    public void startCompetition_ReturnsCompetitionDto() throws Exception {
        // given
        Long competitionId = 1L;

        // when
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/competition/{competitionId}/start", competitionId))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var competitionDtoResponse = objectMapper.readValue(response, CompetitionDto.class);

        // then
        assertEquals(Status.QUEUED.toString(), competitionDtoResponse.getStatus());
    }

    @Test
    public void joinParticipant_Success() throws Exception {
        // given
        Long competitionId = 1L;
        long participantId = 1L;

        // when
        var response = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/competition/{competitionId}/join",
                                competitionId)
                        .param("participantId", Long.toString(participantId)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var competitionDtoResponse = objectMapper.readValue(response, CompetitionDto.class);

        // then
        assertEquals(participantId, competitionDtoResponse.getParticipants().get(0));
    }

    @Test
    public void getAllByStatusCompetition_Success() throws Exception {
        // given
        var status = Status.QUEUED;

        // when
        var response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/competition")
                        .param("status", status.toString()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        var competitionDtoResponseList = objectMapper.readValue(response, new TypeReference<List<CompetitionDto>>() {
        });
        // then
        assertEquals(3, competitionDtoResponseList.size());
    }
}
