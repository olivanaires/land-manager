package br.com.ota.backend.repository;

import br.com.ota.backend.model.Payment;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends BaseRepository<Payment, Long> {

    List<Payment> findAllByContractIdOrderByExpirationdateAsc(Long contractId);

    List<Payment> findAllByContractIdAndExpirationdateIsGreaterThanEqual(Long contractId, LocalDate expiratiodate);

}
