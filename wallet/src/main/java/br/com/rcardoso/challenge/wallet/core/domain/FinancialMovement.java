package br.com.rcardoso.challenge.wallet.core.domain;

import br.com.rcardoso.challenge.wallet.core.enums.MovementType;
import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@Table(name = "TB_FINANCIAL_MOVEMENT")
@AllArgsConstructor
@NoArgsConstructor
public class FinancialMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    private Transaction transaction;

    @Column(columnDefinition="Decimal(10,2) default '0.00'", nullable = false)
    private BigDecimal value;

    @Column(length = 140)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 40, nullable = false)
    private MovementType movementType;
}
