package org.jacp.mapper;

import org.jacp.dto.CompetitionDto;
import org.jacp.entity.CompetitionEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author saffchen created on 23.12.2023
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CompetitionMapper {

    CompetitionDto toCompetitionDto(List<Long> tasks, List<Long> participants, int duration, String status);

    List<CompetitionDto> toCompetitionDtoList(List<CompetitionEntity> competitionEntities);

    CompetitionEntity toCompetitionEntity(CompetitionDto competitionDto);
}
