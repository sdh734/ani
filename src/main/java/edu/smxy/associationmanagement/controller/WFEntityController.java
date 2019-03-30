package edu.smxy.associationmanagement.controller;

import edu.smxy.associationmanagement.domain.Association;
import edu.smxy.associationmanagement.domain.JSONResult;
import edu.smxy.associationmanagement.domain.KeyProject;
import edu.smxy.associationmanagement.domain.WFEntity;
import edu.smxy.associationmanagement.services.association.AssociationService;
import edu.smxy.associationmanagement.services.file.FileService;
import edu.smxy.associationmanagement.services.keyproject.KeyProjectService;
import edu.smxy.associationmanagement.services.wfentity.WFEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: associationmanagement
 * @description: 流程实体类Controller
 * @author: SDH
 * @create: 2019-03-27 20:47
 */
@RestController
@EnableAutoConfiguration
public class WFEntityController {
  @Autowired
  WFEntityService wfEntityService;
  @Autowired
  FileService fileService;
  @Autowired
  AssociationService associationService;
  @Autowired
  KeyProjectService keyProjectService;

  /**
   * 根据创建人id 和申请类型id 获得所有在传入申请类型 申请时间段内 由传入创建人创建的流程集合
   *
   * @param typeid   申请类型id
   * @param authorid 创建人id
   * @return
   */
  @PostMapping({"/getRunningApply"})
  public JSONResult getRunningApply(int typeid, int authorid, int usertype) {
    if (usertype == 0) {
      // 协会
      List<WFEntity> wfEntities = wfEntityService.getRunningApply(typeid, authorid);
      if (wfEntities.size() > 0) {
        return JSONResult.build(200, "ok", wfEntities);
      } else {
        return JSONResult.build(500, "error", null);
      }
    } else if (usertype == 1) {
      // 社联管理员获取要审核的申请
      List<WFEntity> wfEntities = wfEntityService.getRunningApplyAdmin(typeid, 1);
      if (wfEntities.size() > 0) {
        return JSONResult.build(200, "ok", wfEntities);
      } else {
        return JSONResult.build(500, "error", null);
      }
    } else {
      // 团委老师获取要审核的申请
      List<WFEntity> wfEntities = wfEntityService.getRunningApplyAdmin(typeid, 2);
      if (wfEntities.size() > 0) {
        return JSONResult.build(200, "ok", wfEntities);
      } else {
        return JSONResult.build(500, "error", null);
      }
    }
  }

  /**
   * 新增流程方法
   *
   * @param file              文件
   * @param applyTitle        流程标题
   * @param applyInfo         流程备注信息
   * @param applytype         申请类型
   * @param authorid          创建人id
   * @param keyprojectname    重点立项项目名称
   * @param keyprojectmanager 重点立项项目负责人
   * @param keyprojectphone   负责人联系方式
   * @return
   */
  @PostMapping({"/addRegistrationInfo"})
  public JSONResult addRegistrationInfo(
          @RequestParam("file") final MultipartFile file,
          @RequestParam("applyTitle") final String applyTitle,
          @RequestParam("applyInfo") final String applyInfo,
          @RequestParam("applytype") int applytype,
          @RequestParam("authorid") int authorid,
          @RequestParam("keyprojectname") String keyprojectname,
          @RequestParam("keyprojectmanager") String keyprojectmanager,
          @RequestParam("keyprojectphone") String keyprojectphone,
          @RequestParam("keyid") int keyid) {

    WFEntity wfEntity = new WFEntity();
    wfEntity.setAuthorId(authorid);
    wfEntity.setInfo(applyInfo);
    wfEntity.setStatus(1);
    wfEntity.setTitle(applyTitle);
    wfEntity.setTypeId(applytype);
    wfEntity.setIsClose(0);
    edu.smxy.associationmanagement.domain.File file1 = null;
    if (! file.isEmpty()) {
      try {
        String filename = file.getOriginalFilename();
        String path = "";
        if (applytype == 1 || applytype == 2) {
          path = "G:\\upload\\apply\\KeyProject\\";
        } else {
          path = "G:\\upload\\apply\\AssReg\\";
        }

        file.transferTo(new File(path + filename));
        file1 = new edu.smxy.associationmanagement.domain.File();
        file1.setApplyType(applytype);
        file1.setAuthorid(authorid);
        file1.setFilename(filename);
        file1.setFilepath(path);
        file1.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        fileService.uploadFile(file1);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    switch (applytype) {
      // 重点立项申报
      case 1:
        KeyProject keyProject = new KeyProject();
        keyProject.setApplyfileId(fileService.selectByRecord(file1).getId());
        keyProject.setAssId(authorid);
        keyProject.setManager(keyprojectmanager);
        keyProject.setName(keyprojectname);
        keyProject.setManagerPhone(keyprojectphone);
        keyProject.setStatus(0);
        keyProjectService.insert(keyProject);
        keyProject = keyProjectService.selectByRecord(keyProject);
        wfEntity.setDataTablename("t_keyproject");
        wfEntity.setDataTableid(keyProject.getId());
        break;
      // 重点立项结项申报
      case 2:
        KeyProject keyProject1 = keyProjectService.selectByPrimaryKey(keyid);
        wfEntity.setDataTablename("t_keyproject");
        keyProject1.setFinishfileId(fileService.selectByRecord(file1).getId());
        keyProjectService.updateByPrimaryKey(keyProject1);
        wfEntity.setDataTableid(keyid);
        break;
      // 协会学期注册
      case 3:
        wfEntity.setDataTablename("t_association");
        wfEntity.setDataTableid(authorid);
        Association association = associationService.selectByPrimaryKey(authorid);
        association.setTempFile(fileService.selectByRecord(file1).getId());
        associationService.updateByPrimaryKey(association);
        break;
      // 协会注销申请
      case 4:
        wfEntity.setDataTablename("t_association");
        wfEntity.setDataTableid(authorid);
        Association association1 = associationService.selectByPrimaryKey(authorid);
        association1.setTempFile(fileService.selectByRecord(file1).getId());
        associationService.updateByPrimaryKey(association1);
        break;
      // 协会注册申请
      case 5:
        break;
      default:
        break;
    }
    wfEntity.setCreateTime(new Date());
    int record = wfEntityService.insert(wfEntity);
    if (record > 0) {
      return JSONResult.build(200, "ok", null);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }

  /**
   * 修改申请方法
   *
   * @param file       文件
   * @param applyTitle 申请流程标题
   * @param applyInfo  申请流程备注信息
   * @param id         申请流程id
   * @return
   */
  @PostMapping({"/updateRegistrationInfo"})
  public JSONResult updateRegistrationInfo(
          @RequestParam("file") final MultipartFile file,
          @RequestParam("applyTitle") final String applyTitle,
          @RequestParam("applyInfo") final String applyInfo,
          @RequestParam("id") int id,
          @RequestParam("keyprojectname") String keyprojectname,
          @RequestParam("keyprojectmanager") String keyprojectmanager,
          @RequestParam("keyprojectphone") String keyprojectphone,
          @RequestParam("keyid") int keyid) {
    WFEntity wfEntity = wfEntityService.selectByPrimaryKey(id);
    wfEntity.setTitle(applyTitle);
    wfEntity.setInfo(applyInfo);
    wfEntity.setStatus(1);
    edu.smxy.associationmanagement.domain.File file1 = null;
    switch (wfEntity.getTypeId()) {
      case 1:
        // 重点立项
        KeyProject keyProject = keyProjectService.selectByPrimaryKey(wfEntity.getDataTableid());
        keyProject.setName(keyprojectname);
        keyProject.setManagerPhone(keyprojectphone);
        keyProject.setManager(keyprojectmanager);
        file1 = fileService.searchFileById(keyProject.getApplyfileId());
        keyProjectService.updateByPrimaryKey(keyProject);
        break;
      case 2:
        // 重点立项结项
        KeyProject keyProject1 = keyProjectService.selectByPrimaryKey(wfEntity.getDataTableid());
        keyProject1.setName(keyprojectname);
        keyProject1.setManagerPhone(keyprojectphone);
        keyProject1.setManager(keyprojectmanager);
        file1 = fileService.searchFileById(keyProject1.getFinishfileId());
        keyProjectService.updateByPrimaryKey(keyProject1);
        break;
      case 3:
        // 学期注册
        Association association = associationService.selectByPrimaryKey(wfEntity.getDataTableid());
        file1 = fileService.searchFileById(association.getTempFile());
        break;
      case 4:
        // 协会注销
        Association association1 = associationService.selectByPrimaryKey(wfEntity.getDataTableid());
        file1 = fileService.searchFileById(association1.getTempFile());
        break;
      case 5:
        // 新协会注册 暂时没做
        break;
      default:
        break;
    }
    if (! file.isEmpty() && file1 != null) {
      try {
        file.transferTo(new File(file1.getFilepath() + file.getOriginalFilename()));
        file1.setFilename(file.getOriginalFilename());
        fileService.updateByPrimaryKey(file1);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    int record = wfEntityService.updateByPrimaryKey(wfEntity);
    if (record > 0) {
      return JSONResult.build(200, "ok", null);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }

  /**
   * 退回申请的 这个所有申请类型通用
   *
   * @param id   申请流程实例id
   * @param type 退回类型 3 文件不规范 4 不符合申请要求
   * @return
   */
  @PostMapping({"/cancelApply"})
  public JSONResult cancelApply(int id, int type) {
    WFEntity wfEntity = wfEntityService.selectByPrimaryKey(id);
    switch (type) {
      // 文件不规范退回
      case 3:
        wfEntity.setStatus(3);
        break;
      // 不符合申请要求退回 同时关闭申请流程
      case 4:
        wfEntity.setStatus(4);
        wfEntity.setIsClose(1);
        break;
      default:
        break;
    }
    int record = wfEntityService.updateByPrimaryKey(wfEntity);
    if (record > 0) {
      return JSONResult.build(200, "ok", null);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }

  /**
   * 同意申请方法 目前只写了注册登记的
   *
   * @param id 申请流程id
   * @return
   */
  @PostMapping({"/accessApply"})
  public JSONResult accessApply(int id) {
    WFEntity wfEntity = wfEntityService.selectByPrimaryKey(id);
    switch (wfEntity.getStatus()) {
      // 1 当前状态是社联管理人员审核
      case 1:
        // 设置状态为团委老师审核
        wfEntity.setStatus(2);
        break;
      // 当前状态是团委老师审核
      case 2:
        // 设置状态为已完成
        wfEntity.setStatus(5);
        switch (wfEntity.getTypeId()) {
          case 1:
            // 重点立项
            KeyProject keyProject = keyProjectService.selectByPrimaryKey(wfEntity.getDataTableid());
            keyProject.setStatus(1);
            keyProjectService.updateByPrimaryKey(keyProject);
            break;
          case 2:
            KeyProject keyProject1 = keyProjectService.selectByPrimaryKey(wfEntity.getDataTableid());
            keyProject1.setStatus(2);
            keyProjectService.updateByPrimaryKey(keyProject1);
            wfEntity.setIsClose(1);
            // 重点立项结项
            break;
          case 3:
            // 协会学期注册
            Association association =
                    associationService.selectByPrimaryKey(wfEntity.getDataTableid());
            association.setRegistrationFile(association.getTempFile());
            association.setRegistered(true);
            associationService.updateByPrimaryKey(association);
            wfEntity.setIsClose(1);
            break;
          case 4:
            // 协会注销
            Association association1 =
                    associationService.selectByPrimaryKey(wfEntity.getDataTableid());
            association1.setDeleteFile(association1.getTempFile());
            association1.setRegistered(false);
            association1.setIsDeleted(1);
            associationService.updateByPrimaryKey(association1);
            wfEntity.setIsClose(1);
            break;
          case 5:
            // 协会注册
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }
    int record = wfEntityService.updateByPrimaryKey(wfEntity);
    if (record > 0) {
      return JSONResult.build(200, "ok", null);
    } else {
      return JSONResult.build(500, "error", null);
    }
  }
}
