package org.jacp.service;

import lombok.RequiredArgsConstructor;
import org.jacp.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author saffchen created on 22.02.2024
 */
@Service
@RequiredArgsConstructor
public class CompetitionSolutionProducer {

    private final KafkaTemplate<String, QuestionEntity> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public void produce(QuestionEntity questionEntity) {
        kafkaTemplate.send(topic, questionEntity);
    }
}