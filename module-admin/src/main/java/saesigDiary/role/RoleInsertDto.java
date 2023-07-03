package saesigDiary.role;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class RoleInsertDto {
    @Pattern(regexp = "^ROLE_")
    private String name;

    private String isEnabled;

    private String description;
}
