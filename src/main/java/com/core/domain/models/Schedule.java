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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="schedule")
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // @Column(nullable = false)
    private Boolean caught;
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

    @ManyToOne
    @JoinColumn(name = "keyId", referencedColumnName = "id", nullable = false)
    private Key key;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "registry", nullable = false)
    private User user;

    public Schedule(Boolean caught, LocalDateTime acquisitionDate, LocalDateTime devolutionDate, Key key, User user){
        this.caught = caught;
        this.acquisitionDate = acquisitionDate;
        this.devolutionDate = devolutionDate;
        this.key = key;
        this.user = user;
    }


}
