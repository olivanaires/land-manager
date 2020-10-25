package br.com.ota.backend.controller;

import br.com.ota.backend.model.Payment;
import br.com.ota.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/by-contract/{contractId}")
    public ResponseEntity<List<Payment>> findAllByContractIdOrderByExpirationdateAsc(@PathVariable Long contractId) {
        List<Payment> contracts = paymentService.findAllByContractIdOrderByExpirationdateAsc(contractId);
        return ResponseEntity.ok(contracts);
    }

}
