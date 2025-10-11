package com.kakaotechbootcamp.community.utils.exception;

import com.kakaotechbootcamp.community.utils.exception.customexception.BusinessException;
import com.kakaotechbootcamp.community.utils.exception.customexception.CommonErrorCode;
import com.kakaotechbootcamp.community.utils.response.ApiError;
import com.kakaotechbootcamp.community.utils.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handle(BusinessException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
            .body(ApiResponse.error(ApiError.from(e)));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAny(Exception ex) {
        ApiError apiErr = ApiError.from(CommonErrorCode.INTERNAL_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.error(apiErr));
    }
}
