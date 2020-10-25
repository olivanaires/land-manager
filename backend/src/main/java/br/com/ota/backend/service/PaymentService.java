package br.com.ota.backend.service;

import br.com.ota.backend.model.Payment;
import br.com.ota.backend.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> findAllByContractIdOrderByExpirationdateAsc(Long contractId) {
        return paymentRepository.findAllByContractIdOrderByExpirationdateAsc(contractId);
    }

}