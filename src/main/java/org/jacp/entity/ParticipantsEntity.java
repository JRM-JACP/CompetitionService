package org.jacp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author saffchen created on 03.12.2023
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantsEntity {

    @Id
    @Column(name = "id")
    private Long id;
}