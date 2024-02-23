package org.jacp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author saffchen created on 22.02.2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionEntity {
    private Long id;
    private Long competitionId;
    private String problem;
    private String solution;
    private String tags;
    private String difficulty;
}
