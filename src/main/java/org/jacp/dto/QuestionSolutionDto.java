package org.jacp.dto;

import lombok.Data;

/**
 * @author saffchen created on 22.02.2024
 */
@Data
public class QuestionSolutionDto {
    private Long id;
    private Long competitionId;
    private String solution;
}
