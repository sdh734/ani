package edu.smxy.associationmanagement.domain;

public class User {
	private Integer id;
	private String name;
	private Integer type;
	private String account;
	private String password;
	private String phone;
	private Integer associationid;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = ((name == null) ? null : name.trim());
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(final Integer type) {
		this.type = type;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(final String account) {
		this.account = ((account == null) ? null : account.trim());
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = ((password == null) ? null : password.trim());
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = ((phone == null) ? null : phone.trim());
	}

	public Integer getAssociationid() {
		return this.associationid;
	}

	public void setAssociationid(final Integer associationid) {
		this.associationid = associationid;
	}
}
