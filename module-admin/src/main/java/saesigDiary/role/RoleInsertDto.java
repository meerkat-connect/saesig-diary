package saesigDiary.role;

import lombok.Getter;
import lombok.Setter;
import saesigDiary.domain.role.Role;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RoleInsertDto {
    @Pattern(regexp = "^ROLE_", message="역할명은 ROLE_로 시작해야 합니다.")
    private String name;

    private Character isEnabled;

    private String description;

    private Long upperId;

    public Role toEntity() {
        return null;
    }
}
