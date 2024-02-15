package org.jacp.service;

import lombok.AllArgsConstructor;
import org.jacp.entity.CompetitionEntity;
import org.jacp.enums.Status;
import org.jacp.repositry.CompetitionRepository;
import org.springframework.stereotype.Component;

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
                .orElseThrow(() -> new NoSuchElementException("Competition with queued status are not found"));
    }
}