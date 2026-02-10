package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.Tag;
import com.prp.Hisab.domain.User;
import com.prp.Hisab.domain.dto.request.CreateTagRequest;
import com.prp.Hisab.domain.dto.response.CreateTagResponse;
import com.prp.Hisab.domain.dto.response.ListTagResponse;
import com.prp.Hisab.domain.entity.TagEntity;
import com.prp.Hisab.domain.entity.UserEntity;
import com.prp.Hisab.exception.ResourceNotFoundException;
import com.prp.Hisab.mapper.TagMapper;
import com.prp.Hisab.repository.TagRepository;
import com.prp.Hisab.service.TagService;
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
public class TagServiceImpl implements TagService {

  private final UserContext userContext;
  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  @PersistenceContext private EntityManager entityManager;

  @Override
  @Transactional
  public CreateTagResponse createTag(CreateTagRequest request) {
    User user = userContext.getCurrentUser();

    Tag tag = Tag.builder().name(request.name()).build();

    TagEntity tagEntity = tagMapper.toEntity(tag);

    tagEntity.setCreatedBy(entityManager.getReference(UserEntity.class, user.getId()));

    tagEntity = tagRepository.save(tagEntity);

    return new CreateTagResponse(tagEntity.getId(), tagEntity.getName());
  }

  @Override
  @Transactional(readOnly = true)
  public ListTagResponse listTags() {
    User user = userContext.getCurrentUser();

    List<CreateTagResponse> createTagResponses =
        tagRepository.findAllByCreatedById(user.getId()).stream()
            .map(projection -> new CreateTagResponse(projection.getId(), projection.getName()))
            .toList();

    return new ListTagResponse(createTagResponses);
  }

  @Override
  @Transactional
  public void deleteTag(UUID tagId) {
    User user = userContext.getCurrentUser();

    TagEntity tagEntity =
        tagRepository
            .findByIdAndCreatedById(tagId, user.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Tag not found"));

    tagRepository.delete(tagEntity);
  }
}
