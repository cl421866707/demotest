package cn.my3gods.demotest.mapper;

import cn.my3gods.demotest.entity.StudentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

public interface StudentEntityMapper extends BaseMapper<StudentEntity> {

    @Results({
        @Result(id=true,column="id",property="id"),//主键,不是主键可以不写
        @Result(column="id",property="id"),
        @Result(column="id",property="cityPo",//一对一
            one=@One(
                select="cn.my3gods.demotest.mapper.CityMapper.selectById",
                fetchType= FetchType.EAGER
            )
        )
    })
    @Select("select * from student where id = #{id}")
    StudentEntity selectEagerCascade(@Param("id") Long id);
}
