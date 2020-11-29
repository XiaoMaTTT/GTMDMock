package com.gtmdmock.admin.service;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.core.forward.ForwardTemplate;
import com.gtmdmock.core.httperror.ErrorTemplate;

import java.util.List;

public interface ErrorService {

    ErrorTemplate getErrorByRequestId(Integer requestId);

    ErrorTemplate getErrorOfCore(ErrorTemplate error);

    List<ErrorTemplate> getErrorsOfCore(List<ErrorTemplate> errors);
}
