package konami.pes.nation;

import konami.pes.team.TeamDto;

public class NationDto extends TeamDto{

	private Integer continent;

	public Integer getContinent() {
		return continent;
	}

	public void setContinent(Integer continent) {
		this.continent = continent;
	}
}
