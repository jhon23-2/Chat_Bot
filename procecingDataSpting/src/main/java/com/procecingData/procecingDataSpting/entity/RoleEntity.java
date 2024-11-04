package com.procecingData.procecingDataSpting.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rol")
public class RoleEntity {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
