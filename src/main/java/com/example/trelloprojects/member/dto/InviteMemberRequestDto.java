package com.example.trelloprojects.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InviteMemberRequestDto {

    @NotBlank
    private Long inviterId;

    @Email
    private String inviteeEmail;
}
