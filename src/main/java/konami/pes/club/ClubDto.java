package konami.pes.club;

import konami.pes.team.TeamDto;

public class ClubDto extends TeamDto{

	private Integer league;

	public Integer getLeague() {
		return league;
	}

	public void setLeague(Integer league) {
		this.league= league;
	}
}
