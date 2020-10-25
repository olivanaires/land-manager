package br.com.ota.backend.service;

import br.com.ota.backend.model.Contract;
import br.com.ota.backend.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    public List<Contract> findAll(Sort sort) {
        return contractRepository.findAll(sort);
    }

}
