package de.consol.dus;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
  Optional<Greeting> findByName(String name);
  void deleteByName(String name);
}
