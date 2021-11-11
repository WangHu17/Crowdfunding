package com.example.crowdfunding.mapper;

import com.example.crowdfunding.bean.po.ReturnPO;
import com.example.crowdfunding.bean.po.ReturnPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnPOMapper {
    long countByExample(ReturnPOExample example);

    int deleteByExample(ReturnPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReturnPO record);

    int insertSelective(ReturnPO record);

    List<ReturnPO> selectByExample(ReturnPOExample example);

    ReturnPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReturnPO record, @Param("example") ReturnPOExample example);

    int updateByExample(@Param("record") ReturnPO record, @Param("example") ReturnPOExample example);

    int updateByPrimaryKeySelective(ReturnPO record);

    int updateByPrimaryKey(ReturnPO record);
}