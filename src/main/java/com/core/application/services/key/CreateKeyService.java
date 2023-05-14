package com.core.application.services.key;

import com.core.application.dto.key.CreateKeyDTO;
import com.core.application.dto.key.KeyResponseDTO;
import com.core.domain.entities.Key;
import com.core.domain.repository.key.CreateKeyRepository;
import com.core.infra.repository.jpa.key.CreateKeyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateKeyService {
    @Autowired
    private final CreateKeyRepository createKeyRepository;

    public CreateKeyService(CreateKeyJpaRepository createKeyRepository) {
        this.createKeyRepository = createKeyRepository;
    }

    public KeyResponseDTO execute(CreateKeyDTO data) {
        Key key = new Key(data);
        return this.createKeyRepository.create(key);
    }
}
