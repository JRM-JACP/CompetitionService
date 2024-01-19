package org.jacp.dto;

import lombok.Data;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Tags;

import java.util.List;

/**
 * @author saffchen created on 20.12.2023
 */
@Data
public class SearchDto {
    private int limitTasks;
    private Difficulty difficulty;
    private List<Tags> tagsList;
    private List<ParticipantsDto> participantsDto;
    private int duration;
}
