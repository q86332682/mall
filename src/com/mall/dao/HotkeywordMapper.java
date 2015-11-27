package com.mall.dao;

import com.mall.model.Hotkeyword;
import com.mall.model.HotkeywordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HotkeywordMapper {
    int countByExample(HotkeywordExample example);

    int deleteByExample(HotkeywordExample example);

    int deleteByPrimaryKey(Byte id);

    int insert(Hotkeyword record);

    int insertSelective(Hotkeyword record);

    List<Hotkeyword> selectByExample(HotkeywordExample example);

    Hotkeyword selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") Hotkeyword record, @Param("example") HotkeywordExample example);

    int updateByExample(@Param("record") Hotkeyword record, @Param("example") HotkeywordExample example);

    int updateByPrimaryKeySelective(Hotkeyword record);

    int updateByPrimaryKey(Hotkeyword record);
}