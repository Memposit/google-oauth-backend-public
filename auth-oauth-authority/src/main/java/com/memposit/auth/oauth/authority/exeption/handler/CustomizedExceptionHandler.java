package com.memposit.auth.oauth.authority.exeption.handler;

import com.memposit.auth.oauth.authority.exeption.OAuth2AuthenticationProcessingException;
import com.memposit.auth.oauth.authority.exeption.ErrorMessage;
import com.memposit.auth.oauth.authority.exeption.UserAlreadyExistAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

/**
 * This is Customized response entity class that handles the exception and returns the Error as per
 * the format given in {@link ErrorMessage}
 */
@RestControllerAdvice
@Slf4j
public class CustomizedExceptionHandler {

    /**
     * Handle entity not found exception error message.
     *
     * @param ex      the ex
     * @param request the request
     * @return the error message
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(), new Date(System.currentTimeMillis()), ex.getMessage(), request.getDescription(false));
    }

    /**
     * Handle illegal argument exception error message.
     *
     * @param ex      the ex
     * @param request the request
     * @return the error message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(System.currentTimeMillis()), ex.getMessage(), request.getDescription(false));
    }

    /**
     * HttpMessageNotReadableException
     *
     * @param ex      the exception
     * @param request the request
     * @return errorMessage error message
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(System.currentTimeMillis()),
                "Invalid input passed " + ex.getCause(),
                request.getDescription(false));
    }

    /**
     * DataIntegrityViolationException
     *
     * @param ex      the exception
     * @param request the request
     * @return ErrorMessage error message
     */
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    protected ErrorMessage handleConflict(DataIntegrityViolationException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                new Date(System.currentTimeMillis()),
                "Unique index or primary key violation, please check Input parameters",
                request.getDescription(false));
    }

    /**
     * GlobalExceptionHandler
     *
     * @param ex      the exception
     * @param request the request
     * @return ErrorMessage error message
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleGlobalExceptionHandler(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(System.currentTimeMillis()),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * Handle http request method not supported error message.
     *
     * @param ex      the exception
     * @param headers the header
     * @param status  the status
     * @param request the request
     * @return ErrorMessage error message
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                new Date(System.currentTimeMillis()),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * Handle no handler found exception error message.
     *
     * @param ex      the exception
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return ErrorMessage error message
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(), new Date(System.currentTimeMillis()), ex.getMessage(), request.getDescription(false));
    }

    /**
     * Handle http media type not supported exception error message.
     *
     * @param ex      the exception
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return ErrorMessage error message
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorMessage handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                new Date(System.currentTimeMillis()),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * Handle user already exist error message.
     *
     * @param ex      the exception
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return ErrorMessage error message
     */
    @ExceptionHandler(UserAlreadyExistAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUserAlreadeExist(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(System.currentTimeMillis()),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * Handle auth 2 authentication processing exception error message.
     *
     * @param ex      the exception
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return ErrorMessage error message
     */
    @ExceptionHandler(OAuth2AuthenticationProcessingException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleOAuth2AuthenticationProcessingException(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(System.currentTimeMillis()),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * Handle authentication exception error message.
     *
     * @param ex      the ex
     * @param headers the headers
     * @param status  the status
     * @param request the request
     * @return the error message
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorMessage handleAuthenticationException(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error(ex.getMessage());
        return new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(System.currentTimeMillis()),
                ex.getMessage(),
                request.getDescription(false));
    }

}
