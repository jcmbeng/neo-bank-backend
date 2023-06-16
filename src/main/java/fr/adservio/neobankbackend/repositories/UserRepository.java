package fr.adservio.neobankbackend.repositories;

import fr.adservio.neobankbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
