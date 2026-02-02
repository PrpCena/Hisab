package com.prp.Hisab.controller;

import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.CreateInstitutionResponse;
import com.prp.Hisab.service.InstitutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/institutions")
public class InstitutionController {

  private final InstitutionService institutionService;

  @PutMapping
  ResponseEntity<CreateInstitutionResponse> createInstitution(
      @Valid @RequestBody CreateInstitutionRequest request) {

    CreateInstitutionResponse response = institutionService.createInstitution(request);

    return ResponseEntity.ok(response);
  }
}
