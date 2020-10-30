package br.com.ota.backend.controller;

import br.com.ota.backend.model.Payment;
import br.com.ota.backend.model.vo.MessageResponse;
import br.com.ota.backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/by-contract/{contractId}")
    public ResponseEntity<List<Payment>> findAllByContractIdOrderByExpirationdateAsc(@PathVariable Long contractId) {
        List<Payment> payments = paymentService.findAllByContractIdOrderByExpirationdateAsc(contractId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/load/{paymentId}")
    public ResponseEntity<Payment> findById(@PathVariable Long paymentId) {
        Payment payment = paymentService.findById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/pay")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody Payment payment) {
        paymentService.save(payment);
        return ResponseEntity.ok(new MessageResponse("ok"));
    }

}
