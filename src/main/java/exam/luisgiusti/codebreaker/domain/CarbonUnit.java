package exam.luisgiusti.codebreaker.domain;

import com.fasterxml.jackson.annotation.JsonView;
import exam.luisgiusti.codebreaker.domain.constraints.DNAMatrix;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class CarbonUnit {
	public interface Input {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@DNAMatrix
	@JsonView(Input.class)
	private String[] dna;
	private Boolean isHomoSuperior;
}
