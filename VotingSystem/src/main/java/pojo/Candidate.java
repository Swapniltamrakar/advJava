package pojo;

public class Candidate {
	private int id;
	private String name;
	private String lastName;
	//| id | name   | lastName | party    | votes |
	private String party;
	private int voteCount;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", lastName=" + lastName + ", party=" + party + ", voteCount="
				+ voteCount + "]";
	}
	
	public Candidate(String party,int voteCount) {
		this.party=party;
		this.voteCount=voteCount;
	}
	public Candidate(int id, String name, String lastName, String party, int voteCount) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.party = party;
		this.voteCount = voteCount;
	}
}
