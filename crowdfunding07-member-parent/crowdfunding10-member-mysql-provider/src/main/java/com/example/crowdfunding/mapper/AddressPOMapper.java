package com.example.crowdfunding.mapper;

import java.util.List;

import com.example.crowdfunding.bean.po.AddressPO;
import com.example.crowdfunding.bean.po.AddressPOExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AddressPOMapper {
    long countByExample(AddressPOExample example);

    int deleteByExample(AddressPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AddressPO record);

    int insertSelective(AddressPO record);

    List<AddressPO> selectByExample(AddressPOExample example);

    AddressPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AddressPO record, @Param("example") AddressPOExample example);

    int updateByExample(@Param("record") AddressPO record, @Param("example") AddressPOExample example);

    int updateByPrimaryKeySelective(AddressPO record);

    int updateByPrimaryKey(AddressPO record);
}