package org.jacp.competitionservice.service;

import org.jacp.entity.CompetitionEntity;
import org.jacp.enums.Status;
import org.jacp.repositry.CompetitionRepository;
import org.jacp.service.CompetitionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {
    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionService competitionService;

    private static CompetitionEntity competitionEntity;

    @BeforeAll
    public static void createEntity() {
        competitionEntity = new CompetitionEntity();
    }

    @Test
    public void create_ReturnsCompetitionEntity() {
        // given
        when(competitionRepository.save(competitionEntity)).thenReturn(competitionEntity);

        // when
        var result = competitionService.create(competitionEntity);

        // then
        assertNotNull(result);
        assertEquals(competitionEntity, result);
        verify(competitionRepository, times(1)).save(competitionEntity);
    }

    @Test
    public void getAllByStatus_WithValidStatus() {
        // given
        when(competitionRepository.getAllByStatus(Status.CREATED)).thenReturn(Optional.of(List.of(competitionEntity)));

        // when
        var result = competitionService.getAllByStatus(Status.CREATED);

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(competitionEntity, result.get(0));
        verify(competitionRepository, times(1)).getAllByStatus(Status.CREATED);
    }

    @Test
    public void getAllByStatus_WithInvalidStatus() {
        // given
        when(competitionRepository.getAllByStatus(Status.CREATED)).thenReturn(Optional.empty());

        // when/then
        assertThrows(NoSuchElementException.class, () -> competitionService.getAllByStatus(Status.CREATED));
        verify(competitionRepository, times(1)).getAllByStatus(Status.CREATED);
    }

    @Test
    public void getCompetitionEntity_WithValidId() {
        // given
        Long id = 1L;
        when(competitionRepository.getCompetitionEntityById(id)).thenReturn(competitionEntity);

        // when
        CompetitionEntity result = competitionService.getCompetitionEntity(id);

        // then
        assertNotNull(result);
        assertEquals(competitionEntity, result);
        verify(competitionRepository, times(1)).getCompetitionEntityById(id);
    }

    @Test
    public void startCompetition_WithValidId() {
        // given
        Long id = 1L;
        when(competitionRepository.getCompetitionEntityById(id)).thenReturn(competitionEntity);

        // when
        CompetitionEntity result = competitionService.startCompetition(id);

        // then
        assertNotNull(result);
        assertNotNull(result.getStartDate());
        assertNotNull(result.getEndDate());
        assertEquals(competitionEntity.getStatus(), result.getStatus());
        verify(competitionRepository, times(1)).getCompetitionEntityById(id);
    }

    @Test
    public void joinParticipant_WithValidIds() {
        // given
        Long competitionId = 1L;
        Long participantId = 2L;
        competitionEntity.setParticipants(new ArrayList<>());
        when(competitionRepository.getCompetitionEntityById(competitionId)).thenReturn(competitionEntity);

        // when
        CompetitionEntity result = competitionService.joinParticipant(competitionId, participantId);

        // then
        assertNotNull(result);
        assertTrue(result.getParticipants().contains(participantId));
        verify(competitionRepository, times(1)).getCompetitionEntityById(competitionId);
    }
}
