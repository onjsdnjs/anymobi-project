package io.anymobi.domain.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "email")
@Builder
@NoArgsConstructor @AllArgsConstructor
public class UserDTO {

    private Long id;
    private String userName;
    private String email;
    private String password;

}


