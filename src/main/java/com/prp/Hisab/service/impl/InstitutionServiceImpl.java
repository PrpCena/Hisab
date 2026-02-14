package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.Institution;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.dto.request.CreateInstitutionRequest;
import com.prp.Hisab.domain.dto.request.UpdateInstitutionRequest;
import com.prp.Hisab.domain.dto.response.InstitutionResponse;
import com.prp.Hisab.domain.entity.InstitutionEntity;
import com.prp.Hisab.domain.entity.UserEntity;
import com.prp.Hisab.exception.ResourceNotFoundException;
import com.prp.Hisab.mapper.InstitutionMapper;
import com.prp.Hisab.repository.InstitutionRepository;
import com.prp.Hisab.service.InstitutionService;
import com.prp.Hisab.service.UserContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {

  private final UserContext userContext;
  private final InstitutionRepository institutionRepository;
  private final InstitutionMapper institutionMapper;

  @PersistenceContext private EntityManager entityManager;

  @Override
  @Transactional
  public InstitutionResponse createInstitution(CreateInstitutionRequest request) {
    User user = userContext.getCurrentUser();

    Institution institution = Institution.builder().name(request.name()).build();

    InstitutionEntity institutionEntity = institutionMapper.toEntity(institution);

    institutionEntity.setCreatedBy(entityManager.getReference(UserEntity.class, user.getId()));

    institutionEntity = institutionRepository.save(institutionEntity);

    return new InstitutionResponse(institutionEntity.getId(), institutionEntity.getName());
  }

  @Override
  @Transactional(readOnly = true)
  public List<InstitutionResponse> listInstitutions() {
    User user = userContext.getCurrentUser();

    return institutionRepository.findAllByCreatedById(user.getId()).stream()
        .map(projection -> new InstitutionResponse(projection.getId(), projection.getName()))
        .toList();
  }

  @Override
  @Transactional
  public void deleteInstitution(UUID id) {
    User user = userContext.getCurrentUser();

    int isDeleted = institutionRepository.deleteByIdAndCreatedById(id, user.getId());

    if (isDeleted == 0) {
      throw new ResourceNotFoundException("Institution not found");
    }
  }

  @Override
  @Transactional
  public InstitutionResponse updateInstitution(
      UUID institutionId, UpdateInstitutionRequest request) {
    User user = userContext.getCurrentUser();

    InstitutionEntity entity =
        institutionRepository
            .findByIdAndCreatedById(institutionId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Institution not found"));

    Institution domain = institutionMapper.toDomain(entity);

    domain.rename(request.name());

    institutionMapper.updateEntity(domain, entity);

    entity = institutionRepository.save(entity);

    return new InstitutionResponse(entity.getId(), entity.getName());
  }
}
