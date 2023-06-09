package com.core.domain.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name="registry", nullable = false, unique = true)
    private String registry;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="type", nullable = false)
    // private Byte type;
    @Enumerated(EnumType.ORDINAL)
    private UserType type;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Byte typeUser(String type){
        if(type.equals("Servidor")){
            return 1;
        }
        return 0;
    }

    public User(String registry, String name, UserType type)
    {
        this.registry = registry;
        this.name = name;
        this.type = type;
    }
}
