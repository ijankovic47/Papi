package konami.pes.nation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import konami.pes.team.TeamDto;

public class NationDto extends TeamDto{

	@NotNull(message="Continent id must not be null !")
	@Min(value=1, message="Continent id must be >0 !")
	private Integer continent;

	public Integer getContinent() {
		return continent;
	}

	public void setContinent(Integer continent) {
		this.continent = continent;
	}
}
