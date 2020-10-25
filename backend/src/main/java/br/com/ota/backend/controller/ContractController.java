package br.com.ota.backend.controller;

import br.com.ota.backend.model.Contract;
import br.com.ota.backend.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<Contract> contracts = contractService.findAll(Sort.by("number"));
        return ResponseEntity.ok(contracts);
    }

}
