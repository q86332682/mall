package com.mall.dao;

import com.mall.model.Navigate;
import com.mall.model.NavigateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NavigateMapper {
    int countByExample(NavigateExample example);

    int deleteByExample(NavigateExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(Navigate record);

    int insertSelective(Navigate record);

    List<Navigate> selectByExample(NavigateExample example);

    Navigate selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") Navigate record, @Param("example") NavigateExample example);

    int updateByExample(@Param("record") Navigate record, @Param("example") NavigateExample example);

    int updateByPrimaryKeySelective(Navigate record);

    int updateByPrimaryKey(Navigate record);
}