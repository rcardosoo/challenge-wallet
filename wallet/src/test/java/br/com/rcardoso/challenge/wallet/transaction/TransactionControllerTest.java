package br.com.rcardoso.challenge.wallet.transaction;

import br.com.rcardoso.challenge.wallet.core.domain.dto.TransactionDto;
import br.com.rcardoso.challenge.wallet.core.enums.TransactionType;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    @Sql("/db/transaction/insert-requester01.sql")
    public void createDepositTransaction() throws Exception {
        final var transactionDto = getTransactionDto(TransactionType.DEPOSIT);
        mockMvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(new Gson().toJson(transactionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.requesterAccount", nullValue()))
                .andExpect(jsonPath("$.destinationAccount", equalTo(transactionDto.getRequesterAccount().intValue())))
                .andExpect(jsonPath("$.transactionType", equalTo(TransactionType.DEPOSIT.name())))
                .andExpect(jsonPath("$.value").value(is(transactionDto.getValue()), BigDecimal.class));
    }

    @Test
    @Transactional
    @Sql("/db/transaction/insert-requester02.sql")
    public void createWithdrawTransaction() throws Exception {
        final var transactionDto = getTransactionDto(TransactionType.WITHDRAW);
        mockMvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(new Gson().toJson(transactionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.requesterAccount", equalTo(transactionDto.getRequesterAccount().intValue())))
                .andExpect(jsonPath("$.destinationAccount", nullValue()))
                .andExpect(jsonPath("$.transactionType", equalTo(TransactionType.WITHDRAW.name())))
                .andExpect(jsonPath("$.value").value(is(transactionDto.getValue()), BigDecimal.class));
    }

    @Test
    @Transactional
    @Sql("/db/transaction/insert-requester03.sql")
    public void createTransferTransaction() throws Exception {
        var transactionDto = getTransactionDto(TransactionType.TRANSFER);
        transactionDto.setDestinationAccount(124L);
        mockMvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(new Gson().toJson(transactionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.requesterAccount", equalTo(transactionDto.getRequesterAccount().intValue())))
                .andExpect(jsonPath("$.destinationAccount", equalTo(transactionDto.getDestinationAccount().intValue())))
                .andExpect(jsonPath("$.transactionType", equalTo(TransactionType.TRANSFER.name())))
                .andExpect(jsonPath("$.value").value(is(transactionDto.getValue()), BigDecimal.class));
    }

    @Test
    @Transactional
    @Sql("/db/transaction/insert-requester02.sql")
    public void createPaymentTransaction() throws Exception {
        final var transactionDto = getTransactionDto(TransactionType.PAYMENT);
        mockMvc.perform(post("/transactions")
                        .contentType("application/json")
                        .content(new Gson().toJson(transactionDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.requesterAccount", equalTo(transactionDto.getRequesterAccount().intValue())))
                .andExpect(jsonPath("$.destinationAccount", nullValue()))
                .andExpect(jsonPath("$.transactionType", equalTo(TransactionType.PAYMENT.name())))
                .andExpect(jsonPath("$.value").value(is(transactionDto.getValue()), BigDecimal.class));
    }

    private TransactionDto getTransactionDto(TransactionType type) {
        return TransactionDto.builder()
                .requesterAccount(123L)
                .transactionType(type)
                .value(BigDecimal.valueOf(120.00))
                .build();
    }
}
