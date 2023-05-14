package com.core.domain.entities;

import com.core.application.dto.key.CreateKeyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "key")
@Entity(name = "key")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID sectorId;
    private String number;

    public Key(CreateKeyDTO data) {
        this.sectorId = data.sectorId();
        this.number = data.number();
    }
}
