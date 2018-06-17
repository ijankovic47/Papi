package konami.pes.league;

public class LeagueDto {

	private Integer id;
	private String name;
	private String emblem;
	private Integer nation;
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
	public Integer getNation() {
		return nation;
	}
	public void setNation(Integer nation) {
		this.nation = nation;
	}
}
