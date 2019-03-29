package edu.smxy.associationmanagement.services.member;

import edu.smxy.associationmanagement.domain.Member;
import edu.smxy.associationmanagement.domain.MemberResult;

import java.util.List;

public interface MemberService {
	int deleteByPrimaryKey(final Integer id);

	int insert(final Member record);

	int insertSelective(final Member record);

	Member selectByPrimaryKey(final Integer id);

	int updateByPrimaryKeySelective(final Member record);

	int updateByPrimaryKey(final Member record);

	List<Member> getAllMemberByAssociationId(final Integer id);

	int insertbyexcel(final MemberResult result);
}
