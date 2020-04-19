package com.ea.miushop.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.groups.Default;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.ea.miushop.exception.ValidationException;
import com.ea.miushop.validation.dto.ValidationDto;
import com.ea.miushop.validation.groups.Details;
import com.ea.miushop.validation.groups.Production;

import com.ea.miushop.domain.ProductionStatus;

/*
 * ALL target objects to validate follow the same process.
 * SO here we are used GENERICS to re-use the process...
 */

@Component
public  class GenericValidator {  

     public void  doValidate( ProceedingJoinPoint joinPoint, ValidationDto validationDto) throws Throwable {
     	
    	
     	System.out.println();
     	System.out.println("DOING Validation!");
     	
     	// Using Hibernate validator...
        javax.validation.Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();      	

        /*
         * Get the validation group to validate against
         * ...and Validate!!!
         */
  
        Class<?> group = this.getValidationGroup(validationDto);
         Set<ConstraintViolation<Object>> errors = validator.validate(validationDto.getValidationObject(), group);
         	        	

         /*
          * Any Validation errors?
          * If errors PRINT them...
          * ELSE update status to next step in process AND PRINT success!!!
          */
        Boolean validationSuccess = errors.size() == 0 ? true : false;       
       	if (!validationSuccess) {
      	     for (ConstraintViolation<Object> error : errors) {
      	          System.out.println(error.getPropertyPath() + " " +error.getMessage());
      	     }
      	   	throw new ValidationException(errors);
      	}
       	
       	return;
     }  
     
     /*
      * Based on domain object being processed and it's current status, 
      *  return the current Validation Group class for the domain object
      */
     private Class<?> getValidationGroup(ValidationDto validationDto) throws Throwable {
     	
      	ProductionStatus currentStatus  = validationDto.getStatus();
       
       Class<?> returnClass = Default.class;
       
       switch (currentStatus) {
       case INVALID:
     	  returnClass = Default.class;
    	   break;
    	case BASIC:
   	  returnClass = Details.class;
   		break;

 		case DETAILS:
 		case PRODUCTION:
 	    	  returnClass = Production.class;

       }

       return returnClass;
     }

}

 


