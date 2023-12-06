package org.jacp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Tags;

import java.util.List;

/**
 * @author saffchen created on 04.12.2023
 */
@Data
@AllArgsConstructor
public class FilterQuestionDto {
    private Difficulty difficulty;
    private List<Tags> tags;
}
