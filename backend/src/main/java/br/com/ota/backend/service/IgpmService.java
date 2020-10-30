package br.com.ota.backend.service;

import br.com.ota.backend.model.Igpm;
import br.com.ota.backend.model.Payment;
import br.com.ota.backend.repository.IgpmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IgpmService {

    private final PaymentService paymentService;
    private final IgpmRepository igpmRepository;

    public void save(Igpm igpm) {
        igpmRepository.save(igpm);

        List<Payment> payments = paymentService.findAllByContractIdAndExpirationdateIsGreaterThanEqual(igpm.getContract().getId(), igpm.getDate());
        payments.forEach(p -> {
            final BigDecimal oldValue = p.getValue();
            final BigDecimal newValue = p.getValue().multiply(BigDecimal.ONE.add(igpm.getLatepenalty().divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)));

            p.setIgpm(igpm);
            p.setValue(newValue);
            p.setValuebeforecurrentigpm(oldValue);
            paymentService.save(p);
        });

    }
}
