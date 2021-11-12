package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.po.MemberConfirmInfoPO;
import com.example.crowdfunding.bean.po.MemberLaunchInfoPO;
import com.example.crowdfunding.bean.po.ProjectPO;
import com.example.crowdfunding.bean.po.ReturnPO;
import com.example.crowdfunding.bean.vo.MemberConfirmInfoVO;
import com.example.crowdfunding.bean.vo.MemberLauchInfoVO;
import com.example.crowdfunding.bean.vo.ProjectVO;
import com.example.crowdfunding.bean.vo.ReturnVO;
import com.example.crowdfunding.mapper.*;
import com.example.crowdfunding.service.api.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-09 18:55
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Autowired
    private ProjectItemPicPOMapper projectItemPicPOMapper;

    @Autowired
    private MemberLaunchInfoPOMapper memberLaunchInfoPOMapper;

    @Autowired
    private MemberConfirmInfoPOMapper memberConfirmInfoPOMapper;

    @Autowired
    private ReturnPOMapper returnPOMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void saveProjectVO(ProjectVO projectVO, Integer memberId) {

        // 一、保存ProjectPO对象
        ProjectPO projectPO = new ProjectPO();
        BeanUtils.copyProperties(projectVO,projectPO);
        projectPO.setMemberId(memberId);
        String createDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        projectPO.setCreateDate(createDate);
        projectPO.setStatus(0);
        projectPOMapper.insertSelective(projectPO);
        // 获取项目的自增id
        Integer projectId = projectPO.getId();

        // 二、保存项目与分类的关联关系信息
        List<Integer> typeIdList = projectVO.getTypeIdList();
        projectPOMapper.insertTypeRelationship(projectId, typeIdList);

        // 三、保存项目与标签的关联关系信息
        List<Integer> tagIdList = projectVO.getTagIdList();
        projectPOMapper.insertTagRelationship(projectId,tagIdList);

        // 四、保存项目中详情图片路径信息
        List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
        projectItemPicPOMapper.insertItemPicPathList(projectId, detailPicturePathList);

        // 五、保存项目发起人信息
        MemberLauchInfoVO memberLauchInfoVO = projectVO.getMemberLauchInfoVO();
        MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
        BeanUtils.copyProperties(memberLauchInfoVO,memberLaunchInfoPO);
        memberLaunchInfoPO.setMemberId(memberId);
        memberLaunchInfoPOMapper.insert(memberLaunchInfoPO);

        // 六、保存项目回报信息
        List<ReturnVO> returnVOList = projectVO.getReturnVOList();
        ArrayList<ReturnPO> returnPOList = new ArrayList<>();
        for (ReturnVO returnVO:returnVOList){
            ReturnPO returnPO = new ReturnPO();
            BeanUtils.copyProperties(returnVO,returnPO);
            returnPOList.add(returnPO);
        }
        returnPOMapper.insertReturnPOList(projectId,returnPOList);

        // 七、保存项目确认信息
        MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
        BeanUtils.copyProperties(memberConfirmInfoVO,memberConfirmInfoPO);
        memberConfirmInfoPO.setMemberId(memberId);
        memberConfirmInfoPOMapper.insert(memberConfirmInfoPO);

    }

}
