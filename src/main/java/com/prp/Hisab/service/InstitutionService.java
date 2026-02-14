package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.request.UpdateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.InstitutionResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface InstitutionService {

  InstitutionResponse createInstitution(CreateInstitutionRequest request);

  List<InstitutionResponse> listInstitutions();

  void deleteInstitution(UUID id);

  InstitutionResponse updateInstitution(
      UUID institutionId, @Valid UpdateInstitutionRequest request);
}
