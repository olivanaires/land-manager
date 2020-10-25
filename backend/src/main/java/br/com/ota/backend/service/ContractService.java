package br.com.ota.backend.service;

import br.com.ota.backend.configuration.util.MessagePropertie;
import br.com.ota.backend.model.Contract;
import br.com.ota.backend.repository.ContractRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final MessagePropertie messagePropertie;

    public List<Contract> findAll(Sort sort) {
        return contractRepository.findAll(sort);
    }

    public Contract findById(long id) throws NotFoundException {
        return contractRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(messagePropertie.getMessage("message.model.not.found", "model.user")));
    }
}
