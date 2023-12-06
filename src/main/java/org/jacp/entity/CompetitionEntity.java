package org.jacp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
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

    @Column(name = "name")
    private String problem;

    @Column(name = "description")
    private String description;

    @OneToMany
    @Column(name = "participants")
    private List<ParticipantsEntity> participants;

    @Column(name = "startDate")
    private DateFormat startDate;

    @Column(name = "endDate")
    private DateFormat endDate;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "tasks")
    private String body;
}