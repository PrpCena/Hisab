package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.Institution;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.CreateInstitutionResponse;
import com.prp.Hisab.domain.dto.response.ListInstitutionResponse;
import com.prp.Hisab.domain.entity.InstitutionEntity;
import com.prp.Hisab.mapper.InstitutionMapper;
import com.prp.Hisab.repository.InstitutionRepository;
import com.prp.Hisab.service.InstitutionService;
import com.prp.Hisab.service.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

  private final UserContext userContext;
  private final InstitutionRepository institutionRepository;
  private final InstitutionMapper institutionMapper;

  @Override
  @Transactional
  public CreateInstitutionResponse createInstitution(CreateInstitutionRequest request) {
    User user = userContext.getCurrentUser();

    Institution institution = Institution.builder().name(request.name()).createdBy(user).build();

    InstitutionEntity institutionEntity = institutionMapper.toEntity(institution);

    institutionEntity = institutionRepository.save(institutionEntity);

    return institutionMapper.toResponse(institutionMapper.toDomain(institutionEntity));
  }

  @Override
  @Transactional(readOnly = true)
  public ListInstitutionResponse listInstitutions() {
    User user = userContext.getCurrentUser();

    List<CreateInstitutionResponse> institutionDtos =
        institutionRepository.findAllByCreatedById(user.getId()).stream()
            .map(
                projection ->
                    new CreateInstitutionResponse(projection.getId(), projection.getName()))
            .toList();

    return new ListInstitutionResponse(institutionDtos);
  }
}
