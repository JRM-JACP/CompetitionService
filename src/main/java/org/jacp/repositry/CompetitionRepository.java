package org.jacp.repositry;

import org.jacp.entity.CompetitionEntity;
import org.jacp.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author saffchen created on 04.12.2023
 */
@Transactional
@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {
    Optional<List<CompetitionEntity>> getAllByStatus(Status status);

    CompetitionEntity getCompetitionEntityById(Long id);
}
