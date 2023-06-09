package com.core.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable {

    @Column(name="registry", nullable = false, unique = true)
    private String registry;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="type", nullable = false)
    private String type;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(String registry, String name, String type)
{
        this.registry = registry;
        this.name = name;
        this.type = type;
    }
}
