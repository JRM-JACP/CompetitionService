package org.jacp.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author saffchen created on 23.12.2023
 */
@Data
public class CompetitionDto {
    private Long id;
    private List<Long> participants;
    private Date createDate;
    private Date startDate;
    private Date endDate;
    private int duration;
    private String status;
    private List<Long> tasks;

    public void calculateEndDate() {
        long durationInMilliseconds = duration * 1000L;
        long ms = startDate.getTime() + durationInMilliseconds;
        this.endDate = new Date(ms);
    }

    public void joinParticipant(Long participantId) {
        participants.add(participantId);
    }
}
