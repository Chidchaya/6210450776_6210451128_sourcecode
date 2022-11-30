package th.ac.ku.ring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.ac.ku.ring.model.Rings;

import java.util.UUID;

@Repository
public interface RingsRepository extends JpaRepository<Rings , UUID> {
}
