package com.rocketseat.planner.controller;
import com.rocketseat.planner.domain.entity.trip.Trip;
import com.rocketseat.planner.dtos.CreateTripRequestDTO;
import com.rocketseat.planner.dtos.CreateTripResponseDTO;
import com.rocketseat.planner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/trips")
public class TripController {

    @Autowired
    private TripService tripService;

   @PostMapping
   public ResponseEntity<CreateTripResponseDTO> createTrip(@RequestBody CreateTripRequestDTO payload) {
       CreateTripRequestDTO createTripRequestDTO = new CreateTripRequestDTO(payload.destination(), payload.starts_at(),
               payload.ends_at(), payload.emails_to_invite(), payload.owner_email(), payload.owner_name());
       return ResponseEntity.status(HttpStatus.CREATED).body(this.tripService.create(createTripRequestDTO));
   }

   @GetMapping("/{id}")
   public ResponseEntity<Trip> getTripeDetails(@PathVariable UUID id) {
       return this.tripService.getDetails(id);
   }

   @PutMapping("/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable UUID id, CreateTripRequestDTO payload) {
        return this.tripService.update(id, payload);
   }


   @GetMapping("/{id}/confirm")
    public ResponseEntity<?> tripConfirm(@PathVariable UUID id) {
       return this.tripService.confirm(id);
   }
}
