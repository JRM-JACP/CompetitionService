package org.jacp.dto;

import lombok.Data;

import java.util.List;

/**
 * @author saffchen created on 23.12.2023
 */
@Data
public class CompetitionDto {
    private Long id;
    private List<Long> participants;
    private int duration;
    private String status;
    private List<Long> tasks;
}
