package edu.smxy.associationmanagement.utils;

import java.io.File;

/**
 * @program: associationmanagement
 * @description: 文件路径工具类，统一工具类
 * @author: SDH
 * @create: 2019-04-02 02:25
 */
public class PathUtil {
    /**
     * windows 下路径
     */
    // 服务器目录
    // String path = ""/www/wwwroot/ass/upload/";
    public static String ROOT_PATH = "G:\\upload\\";
    /**
     * 协会注册登记表
     */
    public static String APPLYPATH_ASSREG =
            ROOT_PATH + "apply" + File.separator + "AssReg" + File.separator;
    /**
     * 重点立项文件
     */
    public static String APPLYPATH_KEYPROJECT =
            ROOT_PATH + "apply" + File.separator + "KeyProject" + File.separator;
    /**
     * 申请流程临时目录
     */
    public static String APPLYPATH_TEMPLATE =
            ROOT_PATH + "apply" + File.separator + "Template" + File.separator;
    
    public static String EVENT_FILE = ROOT_PATH + File.separator + "eventfile" + File.separator;
    public static String EVENT_TEMPLATE = ROOT_PATH + "eventtemplate" + File.separator;
    public static String EXCEL_TEMP = ROOT_PATH + "exceltemp" + File.separator;
    public static String GUIDE_INFO = ROOT_PATH + "guideinfo" + File.separator;
    public static String ZIP_TEMP = ROOT_PATH + "ziptemp" + File.separator;
}
