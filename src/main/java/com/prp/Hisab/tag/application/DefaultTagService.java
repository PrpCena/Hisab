package com.prp.Hisab.tag.application;

import com.prp.Hisab.tag.domain.Tag;
import com.prp.Hisab.user.domain.User;
import com.prp.Hisab.tag.api.CreateTagRequest;
import com.prp.Hisab.tag.api.TagResponse;
import com.prp.Hisab.tag.infrastructure.TagEntity;
import com.prp.Hisab.user.infrastructure.UserEntity;
import com.prp.Hisab.common.exception.ResourceNotFoundException;
import com.prp.Hisab.tag.infrastructure.TagMapper;
import com.prp.Hisab.tag.infrastructure.TagRepository;
import com.prp.Hisab.user.application.UserContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultTagService implements TagService {

  private final UserContext userContext;
  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  @PersistenceContext private EntityManager entityManager;

  @Override
  @Transactional
  public TagResponse createTag(CreateTagRequest request) {
    User user = userContext.getCurrentUser();

    Tag tag = Tag.builder().name(request.name()).build();

    TagEntity tagEntity = tagMapper.toEntity(tag);

    tagEntity.setCreatedBy(entityManager.getReference(UserEntity.class, user.getId()));

    tagEntity = tagRepository.save(tagEntity);

    return new TagResponse(tagEntity.getId(), tagEntity.getName());
  }

  @Override
  @Transactional(readOnly = true)
  public List<TagResponse> listTags() {
    User user = userContext.getCurrentUser();

    return tagRepository.findAllByCreatedById(user.getId()).stream()
        .map(projection -> new TagResponse(projection.getId(), projection.getName()))
        .toList();
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
