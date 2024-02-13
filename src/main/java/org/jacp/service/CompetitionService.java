package org.jacp.service;

import lombok.AllArgsConstructor;
import org.jacp.entity.CompetitionEntity;
import org.jacp.repositry.CompetitionRepository;
import org.springframework.stereotype.Component;

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
}