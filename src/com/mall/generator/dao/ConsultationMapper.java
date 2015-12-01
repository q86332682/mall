package com.mall.generator.dao;

import com.mall.generator.model.Consultation;
import com.mall.generator.model.ConsultationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsultationMapper {
    int countByExample(ConsultationExample example);

    int deleteByExample(ConsultationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Consultation record);

    int insertSelective(Consultation record);

    List<Consultation> selectByExample(ConsultationExample example);

    Consultation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Consultation record, @Param("example") ConsultationExample example);

    int updateByExample(@Param("record") Consultation record, @Param("example") ConsultationExample example);

    int updateByPrimaryKeySelective(Consultation record);

    int updateByPrimaryKey(Consultation record);
}