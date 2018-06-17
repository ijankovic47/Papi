package konami.pes.club;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import konami.pes.league.League;
import konami.pes.team.Team;

@Entity
@PrimaryKeyJoinColumn(name="ID")
public class Club extends Team {

	@ManyToOne(fetch=FetchType.EAGER)
	private League league;

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
	
	
}
