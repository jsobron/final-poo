package com.unnoba.socialclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unnoba.socialclub.entities.Charge;
import com.unnoba.socialclub.entities.Member;
import com.unnoba.socialclub.repositories.MemberRepository;

@Service
public class MemberService {
	private MemberRepository memberRepository;

	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public void saveMember(Member member) {
		memberRepository.save(member);
	}

	public void deleteMember(int memberId) {
		memberRepository.deleteById(memberId);
	}

	public Optional<Member> findById(int i) {
		return memberRepository.findById(i);
	}

	public List<Member> getAllDebtMembers(String nameKey, String surnameKey, String idKey) {

		if (nameKey != null && surnameKey != null && idKey != null && !idKey.isEmpty()) {

			return memberRepository.findDebtMembersByNameSurnameAndKey(nameKey, surnameKey, Integer.parseInt(idKey));

		} else if (nameKey != null && surnameKey != null) {
			return memberRepository.findDebtMembersByNameAndSurname(nameKey, surnameKey);

		} else if (nameKey != null && idKey != null && !idKey.isEmpty()) {

			return memberRepository.findDebtMembersByNameSurnameAndKey(nameKey, null, Integer.parseInt(idKey));

		} else if (surnameKey != null && idKey != null && !idKey.isEmpty()) {
			return memberRepository.findDebtMembersByNameSurnameAndKey(null, surnameKey, Integer.parseInt(idKey));

		} else if (nameKey != null) {
			return memberRepository.findDebtMembersByNameSurnameAndKey(nameKey, null, null);

		} else if (surnameKey != null) {

			return memberRepository.findDebtMembersByNameSurnameAndKey(null, surnameKey, null);

		} else if (idKey != null && !idKey.isEmpty()) {

			return memberRepository.findDebtMembersByNameSurnameAndKey(null, null, Integer.parseInt(idKey));
		} else {

			return memberRepository.debtMembers();
		}
	}

	public List<Member> getAllMembers(String nameKey, String surnameKey, String idKey) {

		if (nameKey != null && surnameKey != null && idKey != null && !idKey.isEmpty()) {

			return memberRepository.findAll(nameKey, surnameKey, Integer.parseInt(idKey));

		} else if (nameKey != null && surnameKey != null) {
			return memberRepository.findAllOption2(nameKey, surnameKey);

		} else if (nameKey != null && idKey != null && !idKey.isEmpty()) {

			return memberRepository.findAll(nameKey, null, Integer.parseInt(idKey));

		} else if (surnameKey != null && idKey != null && !idKey.isEmpty()) {
			return memberRepository.findAll(null, surnameKey, Integer.parseInt(idKey));

		} else if (nameKey != null) {
			return memberRepository.findAll(nameKey, null, null);

		} else if (surnameKey != null) {

			return memberRepository.findAll(null, surnameKey, null);

		} else if (idKey != null && !idKey.isEmpty()) {

			return memberRepository.findAll(null, null, Integer.parseInt(idKey));
		} else {

			return memberRepository.findAll();
		}
	}

	public List<Member> findLifeMembers(String nameKey, String surnameKey, String idKey) {

		if (nameKey != null && surnameKey != null && idKey != null && !idKey.isEmpty()) {

			return memberRepository.findLifeMembersByNameSurnameAndKey(nameKey, surnameKey, Integer.parseInt(idKey));

		} else if (nameKey != null && surnameKey != null) {
			return memberRepository.findLifeMembersByNameAndSurname(nameKey, surnameKey);

		} else if (nameKey != null && idKey != null && !idKey.isEmpty()) {

			return memberRepository.findLifeMembersByNameSurnameAndKey(nameKey, null, Integer.parseInt(idKey));

		} else if (surnameKey != null && idKey != null && !idKey.isEmpty()) {
			return memberRepository.findLifeMembersByNameSurnameAndKey(null, surnameKey, Integer.parseInt(idKey));

		} else if (nameKey != null) {
			return memberRepository.findLifeMembersByNameSurnameAndKey(nameKey, null, null);

		} else if (surnameKey != null) {

			return memberRepository.findLifeMembersByNameSurnameAndKey(null, surnameKey, null);

		} else if (idKey != null && !idKey.isEmpty()) {

			return memberRepository.findLifeMembersByNameSurnameAndKey(null, null, Integer.parseInt(idKey));
		} else {

			return memberRepository.findLifeMembers();
		}
	
	}

	public List<Member> getAllMembersWithoutLifeMembers(String nameKey, String surnameKey, String idKey, String addressKey) {

		if (nameKey != null && surnameKey != null && idKey != null && !idKey.isEmpty() && addressKey != null) {

			return memberRepository.findWithoutLifeMembers(nameKey, surnameKey, Integer.parseInt(idKey), addressKey);

		} else if (nameKey != null && surnameKey != null) {
			return memberRepository.findWithoutLifeMembersOption2(nameKey, surnameKey, addressKey);

		} else if (nameKey != null && idKey != null && !idKey.isEmpty()) {

			return memberRepository.findWithoutLifeMembers(nameKey, null, Integer.parseInt(idKey), addressKey);

		} else if (surnameKey != null && idKey != null && !idKey.isEmpty()) {
			return memberRepository.findWithoutLifeMembers(null, surnameKey, Integer.parseInt(idKey), addressKey);

		} else if (nameKey != null) {
			return memberRepository.findWithoutLifeMembers(nameKey, null, null, addressKey);

		} else if (surnameKey != null) {

			return memberRepository.findWithoutLifeMembers(null, surnameKey, null, addressKey);

		} else if (idKey != null && !idKey.isEmpty()) {

			return memberRepository.findWithoutLifeMembers(null, null, Integer.parseInt(idKey), addressKey);
		} else {

			return memberRepository.findAllWithoutLifeMembers();
		}
	}

	public List<Charge> memberCharges(Member member) {
		return memberRepository.memberCharges(member);
	}

	public Member findByMemberId(int id) {
		return memberRepository.findByMemberId(id);
	}

}
