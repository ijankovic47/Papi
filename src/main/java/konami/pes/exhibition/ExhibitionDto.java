package konami.pes.exhibition;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ExhibitionDto {

	private Integer id;
	@NotNull(message="Score 1 must not be null !")
	@Min(value=0, message="Score 1 must be >0 !")
	private Integer score1;
	@NotNull(message="Score 2 must not be null !")
	@Min(value=0, message="Score 2 must be >0 !")
	private Integer score2;
	@NotNull(message="You didn't select player 1 !")
	@Min(value=1, message="Player 1 id must be >0 !")
	private Integer player1;
	@NotNull(message="You didn't select player 2 !")
	@Min(value=1, message="Player 2 id must be >0 !")
	private Integer player2;
	@NotNull(message="You didn't select team 1 !")
	@Min(value=1, message="Team 1 id must be >0 !")
	private Integer team1;
	@NotNull(message="You didn't select team 2 !")
	@Min(value=1, message="Team 2 id must be >0 !")
	private Integer team2;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScore1() {
		return score1;
	}
	public void setScore1(Integer score1) {
		this.score1 = score1;
	}
	public Integer getScore2() {
		return score2;
	}
	public void setScore2(Integer score2) {
		this.score2 = score2;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getPlayer1() {
		return player1;
	}
	public void setPlayer1(Integer player1) {
		this.player1 = player1;
	}
	public Integer getPlayer2() {
		return player2;
	}
	public void setPlayer2(Integer player2) {
		this.player2 = player2;
	}
	public Integer getTeam1() {
		return team1;
	}
	public void setTeam1(Integer team1) {
		this.team1 = team1;
	}
	public Integer getTeam2() {
		return team2;
	}
	public void setTeam2(Integer team2) {
		this.team2 = team2;
	}
}
