package br.com.rcardoso.challenge.wallet.dataprovider;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("select a from Account a where a.id = ?1")
    Account getAccountById(Long id);
}
