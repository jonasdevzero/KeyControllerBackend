package com.core.application.dto.key;

import com.core.domain.entities.Key;

import java.util.UUID;

public record KeyResponseDTO(UUID id, UUID sectorId, String number) {
    public KeyResponseDTO(Key key) {
        this(key.getId(), key.getSectorId(), key.getNumber());
    }
}
