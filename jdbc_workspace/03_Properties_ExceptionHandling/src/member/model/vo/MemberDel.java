package member.model.vo;

import java.sql.Date;

public class MemberDel extends Member {
	
	private Date delDate;

	public MemberDel() {
	}
	public MemberDel(String memberId, String password, String memberName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate, Date delDate) {
		super(memberId, password, memberName, gender, age, email, phone, address, hobby, enrollDate);
		this.delDate = delDate;
	}
	

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	@Override
	public String toString() {
		return "MemberDel [getMemberId()=" + getMemberId() + ", getPassword()=" + getPassword() + ", getMemberName()="
				+ getMemberName() + ", getGender()=" + getGender() + ", getAge()=" + getAge() + ", getEmail()="
				+ getEmail() + ", getPhone()=" + getPhone() + ", getAddress()=" + getAddress() + ", getHobby()="
				+ getHobby() + ", getEnrollDate()=" + getEnrollDate() + ", delDate=" + delDate + "]";
	}
	
	
	
	
	
	
	
	

}
