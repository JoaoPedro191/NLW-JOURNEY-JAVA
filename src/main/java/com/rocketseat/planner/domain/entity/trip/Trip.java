package com.rocketseat.planner.domain.entity.trip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trips")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column (nullable = false)
    private String destination;
    @Column (name = "starts_at", nullable = false)
    private LocalDateTime startsAt;
    @Column (name = "ends_at", nullable = false)
    private LocalDateTime endsAt;
    @Column(name = "is_confirmed", nullable = false)
    private boolean isConfirmed = false;
    @Column(name = "owner_name", nullable = false)
    private String ownerName;
    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;
}
