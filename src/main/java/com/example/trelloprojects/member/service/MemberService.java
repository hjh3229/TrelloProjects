package com.example.trelloprojects.member.service;

import com.example.trelloprojects.common.error.BusinessException;
import com.example.trelloprojects.common.error.ErrorCode;
import com.example.trelloprojects.member.dto.InviteMemberRequestDto;
import com.example.trelloprojects.member.dto.MemberResponseDto;
import com.example.trelloprojects.member.dto.RemoveMemberRequestDto;
import com.example.trelloprojects.member.entity.Invitation;
import com.example.trelloprojects.member.entity.UserWorkspace;
import com.example.trelloprojects.member.enums.InvitationStatus;
import com.example.trelloprojects.member.repository.InvitationRepository;
import com.example.trelloprojects.member.repository.UserWorkspaceRepository;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.user.repository.UserRepository;
import com.example.trelloprojects.workspace.entity.Workspace;
import com.example.trelloprojects.workspace.enums.WorkspaceStatus;
import com.example.trelloprojects.workspace.repository.WorkspaceRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserWorkspaceRepository userWorkspaceRepository;
    private final InvitationRepository invitationRepository;

    private final Random random = new SecureRandom(); // 보안에 더 강력한 난수 생성
    private static final int INVITE_CODE_LENGTH = 8;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // 초대 코드 생성에 사용할 문자와 숫자

    @Transactional(readOnly = true)
    public List<MemberResponseDto> getMembers(Long id) {
        Workspace workspace = findActiveWorkspace(id);

        return workspace.getMembers()
                .stream()
                .map(userWorkspace -> userWorkspace.getUser().toMemberResponseDto())
                .toList();
    }

    @Transactional
    public void inviteMember(Long id, InviteMemberRequestDto requestDto) throws MessagingException {
        Workspace workspace = findActiveWorkspace(id);
        User inviter = findUser(requestDto.getInviterId());
        String inviteeEmail = requestDto.getInviteeEmail();

        String inviteCode = generateInviteCode();
        String inviteUrl = generateInviteUrl(workspace.getId(), inviteCode);

        sendWorkspaceInvitation(inviteeEmail, inviter.getUsername(), workspace.getName(), inviteUrl);
        saveInvitation(workspace, inviter, inviteeEmail, inviteCode);
    }

    @Transactional
    public void removeMember(Long id, RemoveMemberRequestDto requestDto) {
        Workspace workspace = findActiveWorkspace(id);
        User user = findUser(requestDto.getMemberId());

        UserWorkspace userWorkspace = findUserWorkspace(user, workspace);
        userWorkspaceRepository.delete(userWorkspace);
    }

    @Transactional
    public void joinWorkspace(Long id, String inviteCode, User user) {
        Workspace workspace = findActiveWorkspace(id);
        Invitation invitation = findPendingInvitation(inviteCode);
        invitation.markAsAccepted();

        UserWorkspace userWorkspace = new UserWorkspace(user, workspace);
        userWorkspaceRepository.save(userWorkspace);
    }

    @Transactional
    public void leaveWorkspace(Long id, User user) {
        Workspace workspace = findActiveWorkspace(id);
        UserWorkspace userWorkspace = findUserWorkspace(user, workspace);

        userWorkspaceRepository.delete(userWorkspace);
    }

    private void sendWorkspaceInvitation(String inviteeEmail, String inviterName, String workspaceName, String inviteUrl) throws MessagingException {
        String subject = "워크스페이스 초대 메일";
        String content = "<p>안녕하세요,</p>"
                + "<p>" + inviterName + "님이 당신을 " + workspaceName + " 워크스페이스에 초대했습니다. 아래 링크를 클릭해 참여하세요.</p>"
                + "<p><a href=\"" + inviteUrl + "\">워크스페이스 참여하기</a></p>"
                + "<p>감사합니다.</p>";

        sendHtmlEmail(inviteeEmail, subject, content);
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

    private String generateInviteCode() {
        int length = INVITE_CODE_LENGTH;
        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return code.toString();
    }

    private String generateInviteUrl(Long workspaceId, String inviteCode) {
        return "http://localhost:8080/api/workspaces/" + workspaceId + "/members/join?code=" + inviteCode;
    }

    private void saveInvitation(Workspace workspace, User inviter, String inviteeEmail, String inviteCode) {
        Invitation invitation = new Invitation(inviter, workspace, inviteeEmail, inviteCode);
        invitationRepository.save(invitation);
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    private Workspace findActiveWorkspace(Long id) {
        Workspace workspace = findWorkspace(id);

        if (workspace.getStatus() == WorkspaceStatus.DELETED) {
            throw new BusinessException(ErrorCode.DELETED_WORKSPACE);
        }

        return workspace;
    }

    private Workspace findWorkspace(Long id) {
        return workspaceRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.WORKSPACE_NOT_FOUND));
    }

    private UserWorkspace findUserWorkspace(User user, Workspace workspace) {
        return userWorkspaceRepository.findByUserAndWorkspace(user, workspace)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_DOES_NOT_BELONG_TO_WORKSPACE));
    }

    private Invitation findPendingInvitation(String inviteCode) {
        return invitationRepository.findByInviteCodeAndStatus(inviteCode, InvitationStatus.PENDING)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_INVITE_CODE));
    }
}
