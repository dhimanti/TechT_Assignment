package com.assignment.techt.controller;

import com.assignment.techt.exception.ClientNotFoundException;
import com.assignment.techt.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/clients")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(
            @RequestBody TransferRequest transferRequest) {
        try {
            transactionService.transferMoney(
                    transferRequest.getFromClientId(),
                    transferRequest.getToClientId(),
                    transferRequest.getAmount());
            return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
        } catch (IllegalArgumentException | ClientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Existing methods...

    public static class TransferRequest {
        private Long fromClientId;
        private Long toClientId;
        private BigDecimal amount;

        // Getters and setters

        public Long getFromClientId() {
            return fromClientId;
        }

        public void setFromClientId(Long fromClientId) {
            this.fromClientId = fromClientId;
        }

        public Long getToClientId() {
            return toClientId;
        }

        public void setToClientId(Long toClientId) {
            this.toClientId = toClientId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }
}
