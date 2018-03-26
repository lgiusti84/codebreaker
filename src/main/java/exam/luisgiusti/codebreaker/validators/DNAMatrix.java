package exam.luisgiusti.codebreaker.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DNAMatrixValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DNAMatrix {
	String message() default "Invalid DNA chain";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
