package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.CreateInstitutionResponse;
import com.prp.Hisab.domain.dto.response.ListInstitutionResponse;
import java.util.UUID;

public interface InstitutionService {

  CreateInstitutionResponse createInstitution(CreateInstitutionRequest request);

  ListInstitutionResponse listInstitutions();

  void deleteInstitution(UUID id);
}
