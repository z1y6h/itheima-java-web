package com.soukou.mapper;


import com.soukou.pojo.Clazz;
import com.soukou.pojo.ClazzQueryParam;
import com.soukou.pojo.Student;
import com.soukou.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time) " +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);

    void deleteByIds(List<Integer> ids);

    void violation(Integer id, Integer score);

    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String, Object>> countClazzData();

    @MapKey("name")
    List<Map> countDegreeData();

    Integer countByClazzId(Integer id);
}
