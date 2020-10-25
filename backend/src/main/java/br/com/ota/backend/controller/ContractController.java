package br.com.ota.backend.controller;

import br.com.ota.backend.configuration.util.MessagePropertie;
import br.com.ota.backend.model.Contract;
import br.com.ota.backend.model.vo.MessageResponse;
import br.com.ota.backend.service.ContractService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping("/list")
    public ResponseEntity<List<Contract>> list() {
        List<Contract> contracts = contractService.findAll(Sort.by("number"));
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/load/{id}")
    public ResponseEntity<?> load(@PathVariable String id) {
        try {
            Contract contract = contractService.findById(Long.parseLong(id));
            return ResponseEntity.ok(contract);
        } catch (NotFoundException ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
        }
    }

}
