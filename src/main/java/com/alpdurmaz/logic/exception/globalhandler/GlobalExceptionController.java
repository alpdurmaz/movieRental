package com.alpdurmaz.logic.exception.globalhandler;

import com.alpdurmaz.logic.exception.exceptions.*;
import com.alpdurmaz.presentation.exceptions.NoTokenFoundException;
import com.alpdurmaz.presentation.exceptions.RestServiceMovieSearchException;
import com.alpdurmaz.presentation.restservice.models.restmodels.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

   @ExceptionHandler({NoTokenFoundException.class
           , RestServiceMovieSearchException.class
           , MovieListUpdateException.class
           , MovieNotFoundException.class
           , MovieRentFailedException.class
           , MovieReturnFailedException.class
           , RentalListException.class})


    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest webRequest){

       LOGGER.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());

       HttpHeaders headers = new HttpHeaders();

       if(ex instanceof NoTokenFoundException){
           HttpStatus status = HttpStatus.NOT_FOUND;

           NoTokenFoundException ntfe = (NoTokenFoundException)ex;

           return handleNoTokenFoundException(ntfe, headers, status, webRequest);
       }

       if(ex instanceof RestServiceMovieSearchException){
           HttpStatus status = HttpStatus.NOT_FOUND;

           RestServiceMovieSearchException restSerMovSerException = (RestServiceMovieSearchException) ex;

           return handleRestServiceMovieSearchException(restSerMovSerException, headers, status, webRequest );
       }

       if(ex instanceof MovieListUpdateException){
           HttpStatus status = HttpStatus.NOT_MODIFIED;

           MovieListUpdateException movieListUpdateException = (MovieListUpdateException) ex;

           return handleMovieListUpdateException(movieListUpdateException, headers, status, webRequest);
       }

       if(ex instanceof MovieNotFoundException){
           HttpStatus status = HttpStatus.NOT_FOUND;

           MovieNotFoundException movieNotFoundException = (MovieNotFoundException) ex;

           return handleMovieNotFoundException(movieNotFoundException, headers, status, webRequest);
       }

       if(ex instanceof MovieRentFailedException){
           HttpStatus status = HttpStatus.NOT_MODIFIED;

           MovieRentFailedException movieRentFailedException = (MovieRentFailedException) ex;

           return handleMovieRentFailedException(movieRentFailedException, headers, status, webRequest);
       }

       if(ex instanceof MovieReturnFailedException){

           HttpStatus status = HttpStatus.NOT_MODIFIED;

           MovieReturnFailedException movieReturnFailedException = (MovieReturnFailedException) ex;

           return handleMovieReturnFailedException(movieReturnFailedException, headers, status, webRequest);
       }

       if(ex instanceof RentalListException){

           HttpStatus status = HttpStatus.NOT_FOUND;

           RentalListException rentalListException = (RentalListException) ex;

           return handleRentalListException(rentalListException, headers, status, webRequest);
       }

       else {
           if (LOGGER.isWarnEnabled()) {
               LOGGER.warn("Unknown exception type: " + ex.getClass().getName());
           }

           HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
           return handleExceptionInternal((NoTokenFoundException) ex, null, headers, status, webRequest);
       }
   }

    protected ResponseEntity<ApiError> handleNoTokenFoundException(NoTokenFoundException ntfe, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
       List<String> errors = Collections.singletonList(ntfe.getMessage());
       return handleExceptionInternal(ntfe, new ApiError(errors), headers, status, webRequest);
    }

    protected ResponseEntity<ApiError> handleRestServiceMovieSearchException(RestServiceMovieSearchException restSerMovSerException
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest webRequest) {

       List<String> errors = Collections.singletonList(restSerMovSerException.getMessage());
       return handleExceptionInternal(restSerMovSerException, new ApiError(errors), headers, status, webRequest);
    }


    protected ResponseEntity<ApiError> handleMovieListUpdateException(MovieListUpdateException movieListUpdateException
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest webRequest) {

        List<String> errors = Collections.singletonList(movieListUpdateException.getMessage());
        return handleExceptionInternal(movieListUpdateException, new ApiError(errors), headers, status, webRequest);
    }

    protected ResponseEntity<ApiError> handleMovieNotFoundException(MovieNotFoundException movieNotFoundException
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest webRequest) {

        List<String> errors = Collections.singletonList(movieNotFoundException.getMessage());
        return handleExceptionInternal(movieNotFoundException, new ApiError(errors), headers, status, webRequest);
    }

    protected ResponseEntity<ApiError> handleMovieRentFailedException(MovieRentFailedException movieRentFailedException
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest webRequest) {

        List<String> errors = Collections.singletonList(movieRentFailedException.getMessage());
        return handleExceptionInternal(movieRentFailedException, new ApiError(errors), headers, status, webRequest);
    }

    protected ResponseEntity<ApiError> handleMovieReturnFailedException(MovieReturnFailedException movieReturnFailedException
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest webRequest) {

        List<String> errors = Collections.singletonList(movieReturnFailedException.getMessage());
        return handleExceptionInternal(movieReturnFailedException, new ApiError(errors), headers, status, webRequest);
    }

    protected ResponseEntity<ApiError> handleRentalListException(RentalListException rentalListException
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest webRequest) {

        List<String> errors = Collections.singletonList(rentalListException.getMessage());
        return handleExceptionInternal(rentalListException, new ApiError(errors), headers, status, webRequest);
    }

    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError apiError, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

       if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)){
           webRequest.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
       }

       return new ResponseEntity<>(apiError, headers, status);
    }
}
