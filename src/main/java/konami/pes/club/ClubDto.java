package konami.pes.club;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import konami.pes.team.TeamDto;

public class ClubDto extends TeamDto{

	@Min(value=1, message="League id must be >0 !")
	@NotNull(message="League id must not be null !")
	private Integer league;

	public Integer getLeague() {
		return league;
	}

	public void setLeague(Integer league) {
		this.league= league;
	}
}
