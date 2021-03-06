package com.example.crowdfunding.controller;

import com.example.crowdfunding.api.MysqlRemoteService;
import com.example.crowdfunding.bean.vo.*;
import com.example.crowdfunding.config.OSSProperties;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.util.CrowdUtil;
import com.example.crowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-09 18:55
 */
@Controller
public class ProjectConsumerController {

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    // 获取项目详情信息
    @RequestMapping("/get/project/detail/{id}")
    public String getProjectDetail(@PathVariable("id")Integer projectId, ModelMap modelMap) {
        ResultEntity<ProjectDetailVO> resultEntity = mysqlRemoteService.getProjectDetailVORemote(projectId);
        if (ResultEntity.SUCCESS.equals(resultEntity.getResult())){
            ProjectDetailVO projectDetail = resultEntity.getData();
            modelMap.addAttribute("projectDetail",projectDetail);
        }
        return "project-detail";
    }

    // 收集确认信息，并将 ProjectVO 提交到数据库
    @RequestMapping("/create/confirm")
    public String confirm(MemberConfirmInfoVO confirmInfoVO, HttpSession session, ModelMap modelMap) {

        // 1、从session中获取上一步存储的ProjectVO对象
        ProjectVO projectVO = (ProjectVO) session.getAttribute("ProjectVO");
        if (projectVO == null) {
            modelMap.addAttribute("message","上一步的数据失效！");
            return "project_confirm";
        }

        // 2、将确认信息放入 ProjectVO 中
        projectVO.setMemberConfirmInfoVO(confirmInfoVO);

        // 3、从session中获取用户账号id
        MemberLoginVO loginMember = (MemberLoginVO) session.getAttribute(CrowdConstant.MEMBER_LOGIN_ACCOUNT);
        Integer memberId = loginMember.getId();

        // 4、调用远程方法将 ProjectVO 对象存入数据库
        ResultEntity<String> resultEntity = mysqlRemoteService.saveProjectVORemote(projectVO, memberId);
        String result = resultEntity.getResult();
        if (ResultEntity.FAILED.equals(result)){
            // 如果存储失败
            modelMap.addAttribute("message",resultEntity.getMessage());
            return "project_confirm";
        }

        // 5、存储成功就移除session中的ProjectVO对象
        session.removeAttribute("ProjectVO");

        // 6、跳转到完成页面
        return "redirect:http://localhost/project/finish/page.html";

    }

    // 收集回报信息
    @ResponseBody
    @RequestMapping("/create/return/information")
    public ResultEntity<String> saveReturn(ReturnVO returnVO, HttpSession session){

        try {
            // 1、从session中获取到上一步存储的ProjectVO对象
            ProjectVO projectVO = (ProjectVO) session.getAttribute("ProjectVO");
            if (projectVO == null) {
                return ResultEntity.failed("上一步的数据失效！");
            }

            // 2、从 projectVO 对象中获取存储回报信息的集合
            List<ReturnVO> returnVOList = projectVO.getReturnVOList();
            if (returnVOList == null) {
                // 如果没有集合则创建一个，并设置到projectVO中
                returnVOList = new ArrayList<>();
                projectVO.setReturnVOList(returnVOList);
            }
            System.out.println("returnVO："+returnVO);
            // 3、将新增的回报信息添加进集合中
            returnVOList.add(returnVO);

            // 4、将 ProjectVO 对象重新放入session中以更新数据
            session.setAttribute("ProjectVO",projectVO);

            // 5、返回
            return ResultEntity.successWithoutData();

        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }

    }

    // 上传回报图片到OSS
    @ResponseBody
    @RequestMapping("/upload/return/picture")
    public ResultEntity<String> uploadReturnPicToOSS(@RequestParam("returnPicture")MultipartFile returnPicture) throws IOException {

        return CrowdUtil.uploadFileToOss(ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                returnPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                returnPicture.getOriginalFilename());

    }

    // 收集项目及发起人信息并存入session中（第一步）
    @RequestMapping("/create/project/information")
    public String saveProjectInfo(
            // 普通表单信息
            ProjectVO projectVO,
            // 头图
            MultipartFile headerPicture,
            // 详情图
            List<MultipartFile> detailPictureList,
            HttpSession session,
            ModelMap modelMap
    ) throws IOException {

        // 一、上传头图

        // 1、如果头图为空，则没有上传头图，放入提示信息，并回到当前页面
        if (headerPicture.isEmpty()) {
            modelMap.addAttribute("message", "未上传头图");
            return "project-launch";
        }

        // 2、上传头图到OSS
        ResultEntity<String> headPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                headerPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                headerPicture.getOriginalFilename()
        );

        String result = headPicResultEntity.getResult();

        // 3、判断上传是否成功
        if (ResultEntity.SUCCESS.equals(result)){
            // 如果上传成功，将返回的图片访问地址放入ProjectVO中
            String headPicturePath = headPicResultEntity.getData();
            projectVO.setHeaderPicturePath(headPicturePath);

        }else {
            // 如果上传失败，放入提示信息，并回到当前页面
            modelMap.addAttribute("message","头图上传失败");
            return "project-launch";
        }

        // 二、上传详情图

        // 1、如果详情图集合为空，则未上传详情图，放入提示信息，并回到当前页面
        if (detailPictureList == null || detailPictureList.size() == 0) {
            modelMap.addAttribute("message","未上传详情图");
            return "project-launch";
        }

        // 用于存放详情图的OSS访问地址
        ArrayList<String> detailPicturePathList = new ArrayList<>();

        // 2、遍历详情图集合，并一一上传到OSS
        for (MultipartFile detailPic:detailPictureList) {

            // 如果单个详情图为空，放入提示信息，并回到当前页面
            if (detailPic.isEmpty()){
                modelMap.addAttribute("message","获取详情图出错");
                return "project-launch";
            }

            // 上传单个详情图到OSS
            ResultEntity<String> detailPicResultEntity = CrowdUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    detailPic.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    detailPic.getOriginalFilename()
            );

            String result1 = detailPicResultEntity.getResult();

            if (ResultEntity.SUCCESS.equals(result1)) {
                // 如果上传成功，将返回的图片访问地址放入detailPicturePathList中
                String detailPicturePath = detailPicResultEntity.getData();
                detailPicturePathList.add(detailPicturePath);

            }else {
                // 如果上传失败，放入提示信息，并回到当前页面
                modelMap.addAttribute("message","详情图上传失败");
                return "project-launch";

            }
        }

        // 3、将详情图地址集合放入ProjectVO中
        projectVO.setDetailPicturePathList(detailPicturePathList);

        // 三、将ProjectVO存入session中并跳转到下一步页面
        session.setAttribute("ProjectVO",projectVO);

        return "redirect:http://localhost/project/return/info/page.html";

    }

}
