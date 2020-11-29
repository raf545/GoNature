package logic;

public class Visitor {

	private String name, email, id, lastname, phone;

	/* empty contructor creates a nullifyed visitor */
	public Visitor() {
		this.name = null;
		this.email = null;
		this.id = null;
		this.lastname = null;
		this.phone = null;
	}

	public Visitor(String name, String email, String id, String lastname, String phone) {
		this.name = name;
		this.email = email;
		this.id = id;
		this.lastname = lastname;
		this.phone = phone;
	}

	/* setters and getters for the visitor class */
	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		StringBuilder ts = new StringBuilder();
		if (id == null) {
			return "Error";
		}
		ts.append(id);
		ts.append(" ");
		ts.append(name);
		ts.append(" ");
		ts.append(lastname);
		ts.append(" ");
		ts.append(phone);
		ts.append(" ");
		ts.append(email);
		return ts.toString();

	}

}
