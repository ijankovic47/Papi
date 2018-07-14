package konami.pes.continent;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContinentDto {

	private Integer id;
	@NotNull(message="Continent name must not be null !")
	@NotEmpty(message="Continent name must not be empty !")
	private String name;
	private String emblem;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmblem() {
		return emblem;
	}
	public void setEmblem(String emblem) {
		this.emblem = emblem;
	}
}
