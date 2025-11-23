package tn.esprit.spring.event.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.event.demo.Model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // JpaRepository already provides CRUD: save, findAll, findById, deleteById, etc.
}