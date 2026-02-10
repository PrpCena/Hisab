package com.prp.Hisab.service.impl;

import com.prp.Hisab.domain.Tag;
import com.prp.Hisab.domain.dto.request.CreateTagRequest;
import com.prp.Hisab.domain.dto.response.CreateTagResponse;
import com.prp.Hisab.domain.entity.TagEntity;
import com.prp.Hisab.mapper.TagMapper;
import com.prp.Hisab.repository.TagRepository;
import com.prp.Hisab.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  @Override
  @Transactional
  public CreateTagResponse createTag(CreateTagRequest request) {
    Tag tag = Tag.builder().name(request.name()).build();

    TagEntity tagEntity = tagMapper.toEntity(tag);

    tagEntity = tagRepository.save(tagEntity);

    return new CreateTagResponse(tagEntity.getId(), tagEntity.getName());
  }
}
