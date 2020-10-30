package br.com.ota.backend.controller;


import br.com.ota.backend.model.Igpm;
import br.com.ota.backend.model.vo.MessageResponse;
import br.com.ota.backend.service.IgpmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/igpm")
@RequiredArgsConstructor
public class IgpmController {

    private final IgpmService igpmService;

    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody Igpm igpm) {
        igpmService.save(igpm);
        return ResponseEntity.ok(new MessageResponse("ok"));
    }
}
