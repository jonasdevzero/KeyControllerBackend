package com.core.domain.repository.key;

import com.core.application.dto.key.KeyResponseDTO;
import com.core.domain.entities.Key;

public interface CreateKeyRepository {
    KeyResponseDTO create(Key key);
}
