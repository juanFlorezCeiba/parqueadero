package co.com.ceiba.parquedero.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

class ApiError {

	   private int status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   private String debugMessage;

	   private ApiError() {
	       timestamp = LocalDateTime.now();
	   }

	   ApiError(int status) {
	       this();
	       this.status = status;
	   }

	   ApiError(int status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = "Unexpected error";
	       this.debugMessage = ex.getLocalizedMessage();
	   }

	   ApiError(int status, String message, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex.getLocalizedMessage();
	   }

	public void setMessage(String message) {
		// TODO Auto-generated method stub
		this.message = message;
	}
	}
