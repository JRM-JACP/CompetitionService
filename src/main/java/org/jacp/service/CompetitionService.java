package org.jacp.service;

import lombok.AllArgsConstructor;
import org.jacp.entity.CompetitionEntity;
import org.jacp.enums.Status;
import org.jacp.repositry.CompetitionRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author saffchen created on 04.12.2023
 */
@Component
@AllArgsConstructor
public class CompetitionService {

    private CompetitionRepository competitionRepository;

    public CompetitionEntity create(CompetitionEntity competitionEntity) {
        return competitionRepository.save(competitionEntity);
    }

    public List<CompetitionEntity> getAllByStatus(Status status) {
        return competitionRepository.getAllByStatus(status)
                .orElseThrow(() -> new NoSuchElementException("Competition with " + status + " status are not found"));
    }

    public CompetitionEntity getCompetitionEntity(Long id) {
        return competitionRepository.getCompetitionEntityById(id);
    }

    public CompetitionEntity startCompetition(Long id) {
        CompetitionEntity competitionEntity = getCompetitionEntity(id);
        competitionEntity.setStartDate(Date.from(Instant.now()));
        competitionEntity.calculateEndDate();
        competitionEntity.setStatus(Status.QUEUED);
        create(competitionEntity);
        return competitionEntity;
    }

    public CompetitionEntity joinParticipant(Long competitionId, Long participantId) {
        CompetitionEntity competitionEntity = getCompetitionEntity(competitionId);
        competitionEntity.joinParticipant(participantId);
        create(competitionEntity);
        return competitionEntity;
    }
}