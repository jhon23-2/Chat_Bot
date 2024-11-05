package com.procecingData.procecingDataSpting.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_question")
    private String question;

    @Column(name = "date_time_question" , columnDefinition = "VARCHAR(255)")
    private String dateTimeQuestion;

    @OneToOne(targetEntity = ResponseEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "response_id" , referencedColumnName = "id")
    private ResponseEntity responseEntity;
}
