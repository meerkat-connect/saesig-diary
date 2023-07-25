package saesigDiary.role;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class RoleUpdateDto {
    private Long id;

    @Pattern(regexp = "^ROLE_", message="역할명은 ROLE_로 시작해야 합니다.")
    private String name;

    private Character isEnabled;

    private String description;

    private Long upperId;
}
