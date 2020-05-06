package cn.my3gods.demotest.dto.bc;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/5/6 14:31
 */
@Getter
@Setter
public class BcPagination {

    private Long total;
    private Long count;
    @JsonAlias("per_page")
    private Long perPage;
    @JsonAlias("current_page")
    private Long currentPage;
    // "current" - "?page=1&limit=50"
    private Map<String, String> links;
    @JsonAlias("too_many")
    private Boolean tooMany;
    /*
    "pagination": {
            "total": 13,
            "count": 13,
            "per_page": 50,
            "current_page": 1,
            "total_pages": 1,
            "links": {
                "current": "?page=1&limit=50"
            },
            "too_many": false
        }
     */
}
