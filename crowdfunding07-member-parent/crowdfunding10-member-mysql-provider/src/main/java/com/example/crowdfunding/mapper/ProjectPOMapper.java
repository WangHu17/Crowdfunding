package com.example.crowdfunding.mapper;

import java.util.List;

import com.example.crowdfunding.bean.po.ProjectPO;
import com.example.crowdfunding.bean.po.ProjectPOExample;
import com.example.crowdfunding.bean.vo.PortalTypeVO;
import com.example.crowdfunding.bean.vo.ProjectDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ProjectPOMapper {
    long countByExample(ProjectPOExample example);

    int deleteByExample(ProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPO record);

    int insertSelective(ProjectPO record);

    ProjectDetailVO selectProjectDetailByProjectId(Integer id);

    List<PortalTypeVO> selectPortalTypeList();

    List<ProjectPO> selectByExample(ProjectPOExample example);

    ProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByExample(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByPrimaryKeySelective(ProjectPO record);

    int updateByPrimaryKey(ProjectPO record);

    void insertTypeRelationship(@Param("projectId") Integer projectId,@Param("typeIdList") List<Integer> typeIdList);

    void insertTagRelationship(@Param("projectId") Integer projectId,@Param("tagIdList") List<Integer> tagIdList);
}