package br.com.rcardoso.challenge.wallet.dataprovider;

import br.com.rcardoso.challenge.wallet.core.domain.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFinancialMovementRepository extends JpaRepository<FinancialMovement, Long> {

}
