package co.trucom.footballfantokens.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fantokens")
public class FanToken {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "club")
	private String club;

	@Column(name = "price")
	private Double price;

	public FanToken() {
	}

	public FanToken(String id, String club, Double value) {
		this.id = id;
		this.club = club;
		this.price = value;
	}

	public String toString() {
		return new String("[Id=" + id + ", Club=" + club + ", Price=" + price + "]");
	}

	public String getId() {
		return id;
	}

	public String getClub() {
		return club;
	}

	public Double getPrice() {
		return price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setClub(String club) {
		this.club = club;
	}

	public void setPrice(Double value) {
		this.price = value;
	}

}
