package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.request.UpdateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.CreateInstitutionResponse;
import com.prp.Hisab.domain.dto.response.ListInstitutionResponse;
import com.prp.Hisab.domain.dto.response.UpdateInstitutionResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface InstitutionService {

  CreateInstitutionResponse createInstitution(CreateInstitutionRequest request);

  ListInstitutionResponse listInstitutions();

  void deleteInstitution(UUID id);

  UpdateInstitutionResponse updateInstitution(UUID institutionId, @Valid UpdateInstitutionRequest request);
}
