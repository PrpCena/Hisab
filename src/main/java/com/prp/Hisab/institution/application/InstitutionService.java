package com.prp.Hisab.institution.application;

import com.prp.Hisab.institution.api.CreateInstitutionRequest;
import com.prp.Hisab.institution.api.UpdateInstitutionRequest;
import com.prp.Hisab.institution.api.InstitutionResponse;
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
