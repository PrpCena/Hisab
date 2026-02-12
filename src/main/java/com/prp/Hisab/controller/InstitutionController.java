package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.CreateInstitutionResponse;
import com.prp.Hisab.domain.dto.response.ListInstitutionResponse;
import com.prp.Hisab.service.InstitutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/institutions")
public class InstitutionController {

  private final InstitutionService institutionService;

  @PostMapping
  ResponseEntity<CreateInstitutionResponse> createInstitution(
      @Valid @RequestBody CreateInstitutionRequest request) {

    CreateInstitutionResponse response = institutionService.createInstitution(request);

    return ResponseEntity.ok(response);
  }

  @GetMapping
  ResponseEntity<ListInstitutionResponse> listInstitutions() {

    ListInstitutionResponse response = institutionService.listInstitutions();

    return ResponseEntity.ok(response);
  }
  
  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteInstitution(@PathVariable UUID id) {
    institutionService.deleteInstitution(id);
    
    return ResponseEntity.ok().build();
  }
}
