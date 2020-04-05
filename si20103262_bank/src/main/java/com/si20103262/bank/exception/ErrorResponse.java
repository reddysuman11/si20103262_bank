package com.si20103262.bank.exception;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@XmlRootElement(name = "error")
@ToString
@Setter
@Getter
@SuppressWarnings("unused")
public class ErrorResponse  {
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
 
    //General error message about nature of error
	private String message;
 
    //Specific errors in API request processing
   
    private List<String> details;
}