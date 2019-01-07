package io.anymobi.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessagePacket {
    private Long userId;
    private Object data;
}
