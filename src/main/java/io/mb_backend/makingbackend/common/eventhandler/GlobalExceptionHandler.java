package io.mb_backend.makingbackend.common.eventhandler;

import io.mb_backend.makingbackend.common.constant.ErrorCode;
import io.mb_backend.makingbackend.common.dto.ApiResponse;
import io.mb_backend.makingbackend.common.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException exception) {
        ErrorCode code = exception.getErrorCode();

        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.fail(exception.getMessage()));
    }


}
