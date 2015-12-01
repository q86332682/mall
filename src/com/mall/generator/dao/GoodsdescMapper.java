package com.mall.generator.dao;

import com.mall.generator.model.Goodsdesc;
import com.mall.generator.model.GoodsdescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsdescMapper {
    int countByExample(GoodsdescExample example);

    int deleteByExample(GoodsdescExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodsdesc record);

    int insertSelective(Goodsdesc record);

    List<Goodsdesc> selectByExample(GoodsdescExample example);

    Goodsdesc selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodsdesc record, @Param("example") GoodsdescExample example);

    int updateByExample(@Param("record") Goodsdesc record, @Param("example") GoodsdescExample example);

    int updateByPrimaryKeySelective(Goodsdesc record);

    int updateByPrimaryKey(Goodsdesc record);
}