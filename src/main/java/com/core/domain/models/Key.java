package com.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "key")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE key SET deleted_at=CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null ")
public class Key {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String number;

    @ManyToOne
    @JoinColumn(name = "sectorId", referencedColumnName = "id", nullable = false)
    // @JsonIgnore
    private Sector sector;

    @Column (name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    public Key(Sector sectorId, String number) {
        this.sector = sectorId;
        this.number = number;
    }
}
