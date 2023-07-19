package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.auth.dto.MemberResponse;
import com.WAT.BEJURYU.dto.MemberChangeNicknameRequest;
import com.WAT.BEJURYU.entity.Member;
import com.WAT.BEJURYU.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(final Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 정보입니다."));
    }

    public boolean notExistById(final Long id) {
        return !memberRepository.existsById(id);
    }

    public MemberResponse getMember(final Long id) {
        final Member member = findById(id);

        return MemberResponse.from(member);
    }

    @Transactional
    public void save(final Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public MemberResponse updateNickname(MemberChangeNicknameRequest request) {
        final Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        member.updateNickname(request.getNewNickname());

        return MemberResponse.from(member);
    }
}
