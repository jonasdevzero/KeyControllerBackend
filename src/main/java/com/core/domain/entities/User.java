package com.core.domain.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import java.util.UUID;
import com.core.application.dto.user.CreateUserDTO;
import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
// import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "users")
@Getter
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String type;
    
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User(CreateUserDTO data){
        this.name = data.name();
        this.type = data.type();
    }
}
