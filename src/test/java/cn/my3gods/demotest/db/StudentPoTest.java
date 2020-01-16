package cn.my3gods.demotest.db;

import cn.my3gods.demotest.dto.CityDto;
import cn.my3gods.demotest.dto.StudentDto;
import cn.my3gods.demotest.entity.CityPo;
import cn.my3gods.demotest.entity.StudentEntity;
import cn.my3gods.demotest.entity.StudentPo;
import cn.my3gods.demotest.mapper.CityMapper;
import cn.my3gods.demotest.mapper.StudentEntityMapper;
import cn.my3gods.demotest.mapper.StudentMapper;
import cn.my3gods.demotest.util.BeanUtils;
import cn.my3gods.demotest.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Objects;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentPoTest {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private StudentEntityMapper studentEntityMapper;

    @Test
    public void test() throws JsonProcessingException {
        StudentPo studentPo = studentMapper.selectById(1);
        CityPo cityPo = cityMapper.selectById(studentPo.getCityId());
        StudentDto studentDto = Objects.requireNonNull(BeanUtils.copyProperties2Bean(studentPo, StudentDto.class));
        studentDto.setCity(BeanUtils.copyProperties2Bean(cityPo, CityDto.class));
        String json = JsonUtils.object2Json(studentDto);
        System.err.println(json);
    }

    @Test
    public void testCascade() throws JsonProcessingException {
        StudentEntity studentEntity = studentEntityMapper.selectEagerCascade((long) 1);
        System.err.println(studentEntity);
        CityPo cityPo = studentEntity.getCityPo();
        System.err.println(cityPo);
        System.err.println(studentEntity);
    }

}
