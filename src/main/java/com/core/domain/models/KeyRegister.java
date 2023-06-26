package com.core.domain.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="keyRegister")
@NoArgsConstructor
@AllArgsConstructor
public class KeyRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private boolean returned;
    private LocalDateTime acquisitionDate;    
    private LocalDateTime devolutionDate; 
    
    @Column (name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //@Column(name = "delete_at")
    // private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "keyId", referencedColumnName = "id")
    private Key key;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "registry")
    private User user;

}
