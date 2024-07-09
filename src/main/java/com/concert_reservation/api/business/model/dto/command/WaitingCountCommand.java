
package com.concert_reservation.api.business.model.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingCountCommand {
    private Long contId;
    private Long tokenId;
    private String userId;
    private Long count;
}