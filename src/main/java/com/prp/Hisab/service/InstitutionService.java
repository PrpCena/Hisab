package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.CreateInstitutionResponse;

public interface InstitutionService {

  CreateInstitutionResponse createInstitution(CreateInstitutionRequest request);
}
