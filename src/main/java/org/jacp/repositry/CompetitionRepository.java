package org.jacp.repositry;

import org.jacp.entity.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author saffchen created on 04.12.2023
 */
@Transactional
@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {
}