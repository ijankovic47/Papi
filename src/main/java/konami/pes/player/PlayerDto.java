package konami.pes.player;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlayerDto {

	private Integer id;
	@NotNull(message="Player name must not be null !")
	@NotEmpty(message="Player name must not be empty !")
	private String name;
	private String image;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
