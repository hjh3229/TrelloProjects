package com.example.trelloprojects.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InviteMemberRequestDto {

    @NotNull
    private Long inviterId;

    @Email
    private String inviteeEmail;
}
