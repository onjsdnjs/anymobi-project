package io.anymobi.domain.dto.hr;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "email")
@Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberDto {

    private int id;
    private String email;
    private String password;

}


