package cn.my3gods.demotest.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto implements Serializable {

    private Long id;
    private String stuNo;
    private String stuName;
    private String stuSex;
    private Integer stuAge;
    private String stuSchool;
    private CityDto city;
}
