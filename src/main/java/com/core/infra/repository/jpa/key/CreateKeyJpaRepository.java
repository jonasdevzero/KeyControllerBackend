package com.core.infra.repository.jpa.key;

import com.core.application.dto.key.KeyResponseDTO;
import com.core.domain.entities.Key;
import com.core.domain.repository.key.CreateKeyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CreateKeyJpaRepository implements CreateKeyRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public KeyResponseDTO create(Key key) {
        entityManager.persist(key);
        return new KeyResponseDTO(key);
    }
}
