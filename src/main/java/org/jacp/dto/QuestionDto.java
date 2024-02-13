package org.jacp.dto;

import lombok.Data;

import java.util.List;

/**
 * @author saffchen created on 07.12.2023
 */
@Data
public class QuestionDto {
    private Long id;
    private String problem;
    private String difficulty;
    private List<String> tags;
    private String description;
    private String body;
}
