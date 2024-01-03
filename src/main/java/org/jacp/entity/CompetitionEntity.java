package org.jacp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jacp.enums.Status;

import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * @author saffchen created on 03.12.2023
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competition")
public class CompetitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "participants")
    @ElementCollection
    @CollectionTable(name = "competition_participants",
            joinColumns = @JoinColumn(name = "competition_id"))
    private List<Long> participants;

    @Column(name = "create_date")
    private Date createDate = Date.from(Instant.now());

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "tasks")
    @ElementCollection
    @CollectionTable(name = "competition_tasks",
            joinColumns = @JoinColumn(name = "competition_id"))
    private List<Long> tasks;
}