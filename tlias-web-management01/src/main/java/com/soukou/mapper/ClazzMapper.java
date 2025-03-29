package com.soukou.mapper;

import com.soukou.pojo.Clazz;
import com.soukou.pojo.ClazzQueryParam;
import com.soukou.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ClazzMapper {
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    @Delete("delete from clazz where id = #{id}")
    void delete(Integer id);

    @Select("select * from clazz")
    List<Clazz> findAll();
}
