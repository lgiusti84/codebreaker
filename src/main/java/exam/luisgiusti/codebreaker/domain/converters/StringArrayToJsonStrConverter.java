package exam.luisgiusti.codebreaker.domain.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter(autoApply = true)
public class StringArrayToJsonStrConverter implements AttributeConverter<String[], String> {

	@Override
	public String convertToDatabaseColumn(String[] attribute) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String[] convertToEntityAttribute(String dbData) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(dbData, String[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String[0];
	}
}
