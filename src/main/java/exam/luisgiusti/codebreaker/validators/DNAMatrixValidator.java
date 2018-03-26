package exam.luisgiusti.codebreaker.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DNAMatrixValidator implements ConstraintValidator<DNAMatrix, String[]> {
	private static final String DNA_REGEX_PATTERN = "^[AGTC]+$";

	@Override
	public boolean isValid(String[] dna, ConstraintValidatorContext constraintValidatorContext) {
		int length = dna.length;
		Pattern pattern = Pattern.compile(DNA_REGEX_PATTERN);
		Stream<String> dnaStream = Arrays.stream(dna);


		return length >= 4 && dnaStream.allMatch(s -> s.length() == length && pattern.matcher(s).find() );
	}
}
