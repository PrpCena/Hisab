package com.prp.Hisab.institution.api;

import com.prp.Hisab.institution.application.InstitutionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/institutions")
public class InstitutionController {

  private final InstitutionService institutionService;

  @PostMapping
  ResponseEntity<InstitutionResponse> createInstitution(
      @Valid @RequestBody CreateInstitutionRequest request) {

    InstitutionResponse response = institutionService.createInstitution(request);

    return ResponseEntity.ok(response);
  }

  @GetMapping
  ResponseEntity<List<InstitutionResponse>> listInstitutions() {

    List<InstitutionResponse> response = institutionService.listInstitutions();

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{institutionId}")
  ResponseEntity<Void> deleteInstitution(@PathVariable UUID institutionId) {
    institutionService.deleteInstitution(institutionId);

    return ResponseEntity.ok().build();
  }

  @PutMapping(path = "/{institutionId}")
  ResponseEntity<InstitutionResponse> updateInstitution(
      @PathVariable UUID institutionId, @Valid @RequestBody UpdateInstitutionRequest request) {
    InstitutionResponse response = institutionService.updateInstitution(institutionId, request);

    return ResponseEntity.ok(response);
  }
}
