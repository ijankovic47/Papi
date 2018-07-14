package konami.pes.league;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LeagueDto {

	private Integer id;
	@NotNull(message="League name must not be null !")
	@NotEmpty(message="League name must not be empty !")
	private String name;
	private String emblem;
	@NotNull(message="Nation id must not be null !")
	@Min(value=1, message="Nation id must be >0 !")
	private Integer nation;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {

	}
	public String getEmblem() {
		return emblem;
	}
	public void setEmblem(String emblem) {
		this.emblem = emblem;
	}
	public Integer getNation() {
		return nation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setNation(Integer nation) {
		this.nation = nation;
	}
}
