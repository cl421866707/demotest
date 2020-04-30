package cn.my3gods.demotest.enums;

import lombok.Getter;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/4/30 14:42
 */
@Getter
public enum BcReqHeaderEnum {

    X_AUTH_TOKEN("X-Auth-Token", "pfqgaayrz6zioodd3ik4ugt5qh0i12b"),

    X_AUTH_CLIENT("X-Auth-Client", "husloz0w5nkqz8x6g0evd0equzvhd86");

    private String name;

    private String value;

    BcReqHeaderEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
