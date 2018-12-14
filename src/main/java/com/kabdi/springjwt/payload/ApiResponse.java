package com.kabdi.springjwt.payload;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * Created by Khalid Abdi
 */
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private HttpStatus httpStatus;
    private T t;
    private PaginationResult paginationResult;
    private HttpHeaders responseHeaders;
	private List<T>listDto;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }  

    public ApiResponse(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public ApiResponse(T t, HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
		this.t = t;
	}

	public ApiResponse(List<T> listDto, HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
		this.listDto = listDto;
	}

	public ApiResponse(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}
	
	public ApiResponse(HttpStatus httpStatus, HttpHeaders responseHeaders, List<T> listDto) {
		super();
		this.httpStatus = httpStatus;
		this.responseHeaders = responseHeaders;
		this.listDto = listDto;
	}
	
	public T getResponseBody() {
		if(this.listDto != null){
			return (T) this.listDto;
		}else if(this.t != null){
			return this.t;
		}else if(this.message != null){
			return (T) this.message;
		}
		return null;
	}


	public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



	public HttpStatus getHttpStatus() {
		return httpStatus;
	}



	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public PaginationResult getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult paginationResult) {
		this.paginationResult = paginationResult;
	}

	public List<T> getListDto() {
		return listDto;
	}

	public void setListDto(List<T> listDto) {
		this.listDto = listDto;
	}

	public HttpHeaders getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(HttpHeaders responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	
}
