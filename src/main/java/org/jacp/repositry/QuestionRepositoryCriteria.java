package org.jacp.repositry;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jacp.entity.CompetitionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author saffchen created on 11.03.2024
 */
@Repository
public class QuestionRepositoryCriteria {

    @PersistenceContext
    private final EntityManager entityManager;

    public QuestionRepositoryCriteria(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Long> getTasksFromCurrentCompetition(Long competitionId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompetitionEntity> criteriaQuery = criteriaBuilder.createQuery(CompetitionEntity.class);
        Root<CompetitionEntity> root = criteriaQuery.from(CompetitionEntity.class);

        criteriaQuery.select(root);

        if (competitionId != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("id"), competitionId);
            criteriaQuery.where(predicate);
        }

        CompetitionEntity competitionEntity = entityManager.createQuery(criteriaQuery).getSingleResult();
        return competitionEntity.getTasks();
    }
}
