package com.prp.Hisab.institution.application;

import com.prp.Hisab.common.exception.ResourceNotFoundException;
import com.prp.Hisab.institution.api.CreateInstitutionRequest;
import com.prp.Hisab.institution.api.InstitutionResponse;
import com.prp.Hisab.institution.api.UpdateInstitutionRequest;
import com.prp.Hisab.institution.domain.Institution;
import com.prp.Hisab.institution.infrastructure.InstitutionEntity;
import com.prp.Hisab.institution.infrastructure.InstitutionMapper;
import com.prp.Hisab.institution.infrastructure.InstitutionRepository;
import com.prp.Hisab.user.application.UserContext;
import com.prp.Hisab.user.domain.User;
import com.prp.Hisab.user.infrastructure.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultInstitutionService implements InstitutionService {

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
