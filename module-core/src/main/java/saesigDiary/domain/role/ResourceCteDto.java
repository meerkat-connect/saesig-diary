package saesigDiary.domain.role;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class ResourceCteDto {
    private Long id;
    private String name;
    private String url;
    private Integer depth;
    private Integer ord;

    public ResourceCteDto(Long aLong, String s, String s1, Integer integer, Integer integer1) {
        this.id = aLong;
        this.name = s;
        this.url = s1;
        this.depth = integer;
        this.ord = integer1;
    }
}
