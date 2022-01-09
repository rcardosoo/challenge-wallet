package br.com.rcardoso.challenge.wallet.dataprovider;

import br.com.rcardoso.challenge.wallet.core.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

}
