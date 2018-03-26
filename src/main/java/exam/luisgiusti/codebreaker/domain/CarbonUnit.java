package exam.luisgiusti.codebreaker.domain;

import exam.luisgiusti.codebreaker.validators.DNAMatrix;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonUnit {
	@Id
	@GeneratedValue
	private Long id;
	@Lob
	@DNAMatrix
	private String[] dna;
	private Boolean isHomoSuperior;
}
