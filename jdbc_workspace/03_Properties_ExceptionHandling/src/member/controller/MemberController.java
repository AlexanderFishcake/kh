package member.controller;

import java.util.List;

import member.model.exception.MemberException;
import member.model.service.MemberService;
import member.model.vo.Member;
import member.model.vo.MemberDel;
import member.view.MemberMenu;

public class MemberController {
	
	private MemberService memberService = new MemberService();

	public List<Member> selectAll() {
		List<Member> list = null;
		try {
			list = memberService.selectAll();
		} catch (MemberException e) {
			new MemberMenu().displayError(e.getMessage() + " : 관리자에게 문의하세요");
		}
		return list;
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

	public List<Member> selectName(String memberName) {
		return memberService.selectName(memberName);
	}

	public int deleteId(String memberId) {
		return memberService.deleteId(memberId);
	}

	public List<MemberDel> selectDel() {
		return memberService.selectDel();
	}



}
