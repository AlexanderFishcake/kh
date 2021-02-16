package member.controller;

import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;

public class MemberController {
	
	private MemberService memberService = new MemberService();

	public List<Member> selectAll() {
		return memberService.selectAll();
	}
	
	public int insertMember(Member member) {
		return memberService.insertMember(member);
	}

	public Member selectId(String memberId) {
		return memberService.selectId(memberId);
	}

	public int updateMember(int i, String inputValue, Member member) {
		return memberService.updateMember(i, inputValue, member);
	}



}
