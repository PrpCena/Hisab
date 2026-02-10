package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateTagRequest;
import com.prp.Hisab.domain.dto.response.CreateTagResponse;
import com.prp.Hisab.domain.dto.response.ListTagResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface TagService {
    CreateTagResponse createTag(@Valid CreateTagRequest request);
    
    ListTagResponse listTags();
    
    void deleteTag(UUID tagId);
}
