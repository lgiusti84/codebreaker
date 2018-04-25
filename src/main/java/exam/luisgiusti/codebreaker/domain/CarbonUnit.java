package exam.luisgiusti.codebreaker.domain;

import com.fasterxml.jackson.annotation.JsonView;
import exam.luisgiusti.codebreaker.domain.constraints.DNAMatrix;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
public class CarbonUnit {
	public interface Input {}

	@Id
	private String id;
	@Transient
	@NotNull
	@JsonView(Input.class)
	@DNAMatrix
	private String[] dna;
	private Boolean isHomoSuperior;

	public CarbonUnit() {
	}

	public CarbonUnit(@NotNull String[] dna, Boolean isHomoSuperior) {
		setDna(dna);
		this.isHomoSuperior = isHomoSuperior;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
		updateId();
	}

	public String[] getDna() {
		return this.dna;
	}

	public void setIsHomoSuperior(Boolean isHomoSuperior) {
		this.isHomoSuperior = isHomoSuperior;
	}

	public Boolean getIsHomoSuperior() {
		return this.isHomoSuperior;
	}

	private void updateId() {
		id = "";
		for(String str : dna) {
			id = id.concat(str);
		}
	}
}
