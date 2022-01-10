package br.com.rcardoso.challenge.wallet.dataprovider;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.math.BigDecimal;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    BigDecimal findBalanceById(Long id);
}
