package com.localmood.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler({
		LocalmoodException.class
	})
	public ResponseEntity<?> handle(LocalmoodException e) {
		return ResponseEntity.status(e.getStatus())
			.body(ErrorResponse.from(e));
	}

	@ExceptionHandler({
		MethodArgumentNotValidException.class
	})
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ErrorResponse.from(e));
	}

	@Getter
	static class ErrorResponse {
		private int status;
		private String message;
		private String solution;

		public static ErrorResponse from(LocalmoodException e) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.status = e.getStatus();
			errorResponse.message = e.getMessage();
			errorResponse.solution = e.getSolution();
			return errorResponse;
		}

		public static ErrorResponse from(MethodArgumentNotValidException e) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.status = HttpStatus.BAD_REQUEST.value();
			errorResponse.message = convert(e.getAllErrors());
			errorResponse.solution = "입력 값의 유효성 검증에 실패했습니다. 데이터를 수정하세요.";
			return errorResponse;
		}

		public static String convert(List<ObjectError> errors) {
			return errors.stream()
				.map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		}
	}
}
