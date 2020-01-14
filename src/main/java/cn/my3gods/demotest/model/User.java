package cn.my3gods.demotest.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import java.io.Serializable;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable, Comparable<User> {

    private String id;

    private String name;

    private Integer age;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("name", name)
            .add("age", age)
            .toString();
    }

    @Override
    public int compareTo(@Nonnull User user) {
        return ComparisonChain.start()
            .compare(age, user.age)
            .compare(Integer.parseInt(id), Integer.parseInt(user.id))
            .compare(name,user.name)
            .result();
    }
}
