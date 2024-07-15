package com.rocketseat.planner.services;
import com.rocketseat.planner.domain.entity.trip.Trip;
import com.rocketseat.planner.dtos.CreateTripRequestDTO;
import com.rocketseat.planner.dtos.CreateTripResponseDTO;
import com.rocketseat.planner.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {
    //Validar data inicio data fim pq colocou um objeto representando ISO;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private TripRepository tripRepository;

    public CreateTripResponseDTO create(CreateTripRequestDTO payload) {
        Trip newTripe = new Trip();
        newTripe.setDestination(payload.destination());
        newTripe.setStartsAt(LocalDateTime.parse(payload.starts_at()));
        newTripe.setEndsAt(LocalDateTime.parse(payload.starts_at()));
        newTripe.setOwnerName(payload.owner_name());
        newTripe.setOwnerEmail(payload.owner_email());
        this.tripRepository.save(newTripe);
        return new CreateTripResponseDTO(newTripe.getId());
    }

    public ResponseEntity<Trip> getDetails(UUID tripId) {
         Optional<Trip> trip = this.tripRepository.findById(tripId);
         return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Trip> update(UUID tripId, CreateTripRequestDTO payload) {
        Optional<Trip> trip = this.tripRepository.findById(tripId);

        if (trip.isPresent()) {
            Trip existingTrip = trip.get();
            existingTrip.setDestination(payload.destination());
            existingTrip.setStartsAt(LocalDateTime.parse(payload.starts_at()));
            existingTrip.setEndsAt(LocalDateTime.parse(payload.ends_at()));

            this.tripRepository.save(existingTrip);
            return ResponseEntity.ok(existingTrip);
        }

         return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Trip> confirm(UUID tripId) {
        Optional<Trip> trip = this.tripRepository.findById(tripId);
        if (trip.isPresent()) {
           Trip existingTrip = trip.get();
            existingTrip.setConfirmed(true);
            this.tripRepository.save(existingTrip);
            this.participantService.triggerConfirmationEmailToParticipants(tripId);
            return ResponseEntity.ok(existingTrip);
        }
        return ResponseEntity.notFound().build();
    }
}


//Relacao de 1 pra mut