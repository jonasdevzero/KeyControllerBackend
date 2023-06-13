package com.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import jakarta.persistence.Transient;

@Getter @Setter
@Entity
@Table(name = "key")
@NoArgsConstructor
@AllArgsConstructor
public class Key {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String number;

    @OneToOne
    @JoinColumn(name = "sectorId", referencedColumnName = "id")
    private Sector sector;
    
    @Column (name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Key(Sector sectorId, String number) {
        this.sector = sectorId;
        this.number = number;
    }
}
