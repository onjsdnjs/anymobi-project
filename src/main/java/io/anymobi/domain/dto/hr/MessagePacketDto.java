package io.anymobi.domain.dto.hr;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessagePacketDto {
    private Long userId;
    private Object data;
}

