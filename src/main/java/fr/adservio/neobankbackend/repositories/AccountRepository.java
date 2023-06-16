package fr.adservio.neobankbackend.repositories;

import fr.adservio.neobankbackend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>
{
    Optional<Account> findByAccountNumberIgnoreCase(String accountNumber);
}
