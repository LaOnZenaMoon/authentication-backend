package me.lozm.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.global.code.UseYn;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
public class BaseVo {

    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedDateTime;
    private Long createdBy;
    private Long modifiedBy;
    private UseYn use;

}
