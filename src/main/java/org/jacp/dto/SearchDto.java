package org.jacp.dto;

import lombok.Data;

import java.util.List;

/**
 * @author saffchen created on 20.12.2023
 */
@Data
public class SearchDto {
    private FilterDto filterDto;
    private List<ParticipantDto> participantDto;
    private int duration;
}
