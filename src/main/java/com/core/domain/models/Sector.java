package com.core.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "sector")
@NoArgsConstructor
@AllArgsConstructor
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @Column(name="name", nullable = false)
    private String name;

    // @Column(name = "created_at")
    // @CreationTimestamp
    // private LocalDateTime createAt;
    
    // @Column(name = "updated_at")
    // @UpdateTimestamp
    // private LocalDateTime updatedAt;
    
    // @OneToMany(mappedBy="sectorId",cascade = CascadeType.ALL)
    // @JoinColumn(name="sectorId")
    // private List<Key> key;
    
    public Sector(String name){
        this.name = name;
    }
}
