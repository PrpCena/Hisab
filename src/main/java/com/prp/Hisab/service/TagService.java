package com.prp.Hisab.service;

import com.prp.Hisab.domain.dto.request.CreateTagRequest;
import com.prp.Hisab.domain.dto.response.CreateTagResponse;
import jakarta.validation.Valid;

public interface TagService {
    CreateTagResponse createTag(@Valid CreateTagRequest request);
}
