package org.jacp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jacp.enums.Difficulty;
import org.jacp.enums.Tags;

import java.util.List;

/**
 * @author saffchen created on 06.12.2023
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String problem;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "question_tags", joinColumns = @JoinColumn(name = "question_id"))
    private List<Tags> tags;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String body;
}
