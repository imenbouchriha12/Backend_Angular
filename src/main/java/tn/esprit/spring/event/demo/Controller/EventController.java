package tn.esprit.spring.event.demo.Controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.event.demo.Model.Event;
import tn.esprit.spring.event.demo.Service.EventService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    private final EventService service;
    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    public List<Event> all() {
        return service.findAll();
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")

    @GetMapping("/{id}")
    public Event get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Event event) {
        try {
            Event saved = service.create(event);
            return ResponseEntity.created(URI.create("/api/events/" + saved.getId())).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @Valid @RequestBody Event event) {
        return service.update(id, event);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    // ✅ Like endpoint
    @PreAuthorize("hasRole('CLIENT')")
    @PutMapping("/{id}/like")
    public Event likeEvent(@PathVariable Long id) {
        return service.likeEvent(id);
    }

    // ✅ Dislike endpoint
    @PreAuthorize("hasRole('CLIENT')")
    @PutMapping("/{id}/dislike")
    public Event dislikeEvent(@PathVariable Long id) {
        return service.dislikeEvent(id);
    }
}