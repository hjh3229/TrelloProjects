package com.example.trelloprojects.member.controller;

import com.example.trelloprojects.common.dto.MsgResponseDto;
import com.example.trelloprojects.user.entity.UserDetailsImpl;
import com.example.trelloprojects.member.dto.InviteMemberRequestDto;
import com.example.trelloprojects.member.dto.MemberResponseDto;
import com.example.trelloprojects.member.dto.RemoveMemberRequestDto;
import com.example.trelloprojects.member.service.MemberService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workspaces/{workspaceId}/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembers(@PathVariable Long workspaceId) {
        List<MemberResponseDto> members = memberService.getMembers(workspaceId);
        return ResponseEntity.ok(members);
    }

    @PostMapping("/invite")
    public ResponseEntity<MsgResponseDto> inviteMember(@PathVariable Long workspaceId, @RequestBody InviteMemberRequestDto requestDto) throws MessagingException {
        memberService.inviteMember(workspaceId, requestDto);
        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 멤버 초대 성공", HttpStatus.OK.value()));
    }

    @PostMapping("/remove")
    public ResponseEntity<MsgResponseDto> removeMember(@PathVariable Long workspaceId, @RequestBody RemoveMemberRequestDto requestDto) {
        memberService.removeMember(workspaceId, requestDto);
        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 멤버 삭제 성공", HttpStatus.OK.value()));
    }

    @GetMapping ("/join")
    public ResponseEntity<MsgResponseDto> joinWorkspace(@PathVariable Long workspaceId,
                                                        @RequestParam(name = "code") String inviteCode,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        memberService.joinWorkspace(workspaceId, inviteCode, userDetails.getUser());
        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 멤버 가입 성공", HttpStatus.OK.value()));
    }

    @PostMapping ("/leave")
    public ResponseEntity<MsgResponseDto> leaveWorkspace(@PathVariable Long workspaceId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        memberService.leaveWorkspace(workspaceId, userDetails.getUser());
        return ResponseEntity.ok(new MsgResponseDto("워크스페이스 멤버 탈퇴 성공", HttpStatus.OK.value()));
    }
}
