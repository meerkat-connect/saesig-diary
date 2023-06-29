package saesigDiary.domain.role;

import lombok.Data;

@Data
public class ResourceCteDto {
    private Long id;
    private String name;
    private String url;
    private Integer depth;
    private Integer ord;

    public ResourceCteDto(Long id, String name, String url, Integer depth, Integer ord) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.depth = depth;
        this.ord = ord;
    }
}
