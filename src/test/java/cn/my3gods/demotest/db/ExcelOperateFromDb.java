package cn.my3gods.demotest.db;

import cn.my3gods.demotest.entity.CityPo;
import cn.my3gods.demotest.entity.StudentPo;
import cn.my3gods.demotest.excel.listener.DataListener;
import cn.my3gods.demotest.excel.model.StudentData;
import cn.my3gods.demotest.excel.util.FileUtils;
import cn.my3gods.demotest.mapper.CityMapper;
import cn.my3gods.demotest.mapper.StudentMapper;
import cn.my3gods.demotest.util.BeanUtils;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExcelOperateFromDb {

    private static final String FILE_PATH = FileUtils.WRITE_PATH + FileUtils.excelFileName("studentData");

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private CityMapper cityMapper;

    @Test
    public void writeExcelFromDb() {
        QueryWrapper<StudentPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("id", 11);
        List<StudentPo> studentPos = studentMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(studentPos)) {
            List<StudentData> studentDataList = studentPos.stream().map(studentPo -> {
                CityPo cityPo = cityMapper.selectById(studentPo.getCityId());
                StudentData studentData = Objects.requireNonNull(BeanUtils.copyProperties2Bean(studentPo, StudentData.class));
                studentData.setCityName(cityPo.getName());
                return studentData;
            }).collect(Collectors.toList());
            EasyExcel.write(FILE_PATH, StudentData.class).sheet("学生表格1").doWrite(studentDataList);
        }

    }

    @Test
    public void readExcel2Db(){

    }

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link StudentData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DataListener}
     * <p>3. 直接读即可
     */
    @Test
    public void simpleRead() {

        String targetFilePath = FileUtils.WRITE_PATH + "studentData1579137523988.xlsx";

        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(targetFilePath, StudentData.class, new DataListener()).sheet("学生表格1").doRead();

        /*
        // 写法2：
        ExcelReader excelReader = EasyExcel.read(targetFilePath, StudentData.class, new DataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();*/
    }
}
