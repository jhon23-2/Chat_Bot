package com.procecingData.procecingDataSpting.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Persona")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    @ManyToMany(targetEntity = RoleEntity.class,fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles" , joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(targetEntity = QuestionEntity.class, fetch = FetchType.LAZY)
    @JoinTable(name = "person_question" ,
            joinColumns = @JoinColumn(name = "person_id") ,
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<QuestionEntity> questionEntities = new ArrayList<>();
}
