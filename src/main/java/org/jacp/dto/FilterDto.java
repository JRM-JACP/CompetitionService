package org.jacp.dto;

import lombok.Data;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Tags;

import java.util.List;

/**
 * @author saffchen created on 13.02.2024
 */
@Data
public class FilterDto {
    private int limitTasks;
    private Difficulty difficulty;
    private List<Tags> tagsList;
}
