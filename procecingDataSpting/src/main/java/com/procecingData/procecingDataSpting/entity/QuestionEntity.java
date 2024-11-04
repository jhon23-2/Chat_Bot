package com.procecingData.procecingDataSpting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(targetEntity = ResponseEntity.class)
    @JoinColumn(name = "response_id" , referencedColumnName = "id")
    private ResponseEntity responseEntity;
}
