package cn.my3gods.demotest.dto.bc.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BcBrandDto {

    @JsonProperty(value = "id", access = Access.READ_WRITE)
    private Integer bcId;

    private String name;

    @JsonProperty(value = "image_url", access = Access.READ_WRITE)
    private String imageUrl;

    /*
    原JSON：
{
    "id": 35,
    "name": "Sagaform",
    "page_title": "",
    "meta_keywords": [
        ""
    ],
    "meta_description": "",
    "image_url": "",
    "search_keywords": "",
    "custom_url": {
        "url": "/brands/sagaform/",
        "is_customized": true
    }
}
     */
}
