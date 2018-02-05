package co.com.ceiba.parquedero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.ceiba.parqueadero.model.ErrorMessage;

@ResponseStatus(value = HttpStatus.ACCEPTED)
public class CreatingRegisterException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;

	
	public CreatingRegisterException( String message, int statusCode ) {
		super( message );
		this.statusCode = statusCode;
	}
//	
//	private Integer httpStatus;
////	private String message;
//    private Integer code;
//    private Integer codigoError;
//    
//    
//    
//	public CreatingRegisterException(Integer codigoError) {
//		super();
//		this.codigoError = codigoError;
//	}
//
//
//	public CreatingRegisterException(Integer httpStatus, Integer code, Integer codigoError) {
//		super();
//		this.httpStatus = httpStatus;
//	//	this.message = message;
//		this.code = code;
//		this.codigoError = codigoError;
//	}
//	
//	
//	public Integer getHttpStatus() {
//		return httpStatus;
//	}
//	public void setHttpStatus(Integer httpStatus) {
//		this.httpStatus = httpStatus;
//	}
////	public String getMessage() {
////		return message;
////	}
////	public void setMessage(String message) {
////		this.message = message;
////	}
////	public Integer getCode() {
////		return code;
////	}
//	public void setCode(Integer code) {
//		this.code = code;
//	}
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//
//	public Integer getCodigoError() {
//		return codigoError;
//	}
//
//
//	public void setCodigoError(Integer codigoError) {
//		this.codigoError = codigoError;
//	}
//
//    
//	@Override
//    public String getMessage(){
//         
//        String mensaje="";
//         
//        switch(codigoError){
//            case 111:
//                mensaje="Error, el numero esta entre 0 y 10";
//                break;
//            case 222:
//                mensaje="Error, el numero esta entre 11 y 20";
//                break;
//            case 333:
//                mensaje="Error, el numero esta entre 21 y 30";
//                break;
//        }
//         
//        return mensaje;
//         
//    }
//	
    
}
