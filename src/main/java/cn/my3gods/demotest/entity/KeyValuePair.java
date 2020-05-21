package cn.my3gods.demotest.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * <br></br>
 *
 * @author Charlie
 * @version 1.0
 * @date 2020/5/21 18:12
 */
@Setter
@Getter
public class KeyValuePair {

    private String key;

    private String value;

    public KeyValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
