package es.mabu.mr.restaurant.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosedDayRepository extends JpaRepository<ClosedDay, Long> {

}
