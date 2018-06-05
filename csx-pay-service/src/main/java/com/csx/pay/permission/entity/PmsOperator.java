package com.csx.pay.permission.entity;

/**
 * @author csx
 * @Package com.csx.pay.permission.entity
 * @Description: 权限管理-操作员
 * @date 2018/6/5 0005
 */
public class PmsOperator extends PermissionBaseEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**登录名*/
    private String loginName;
    /**登录密码*/
    private String loginPwd;
    /**姓名*/
    private String realName;
    /**手机号*/
    private String mobileNo;
    /**操作员类型（admin:超级管理员，common:普通操作员），超级管理员由系统初始化时添加，不能删除*/
    private String type;
    /**盐*/
    private String salt;

    /**
     * 登录名
     *
     * @return
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 登录名
     *
     * @return
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 登录密码
     *
     * @return
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 登录密码
     *
     * @return
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * 姓名
     *
     * @return
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 姓名
     *
     * @return
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 手机号
     *
     * @return
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 手机号
     *
     * @return
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * 操作员类型
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 操作员类型
     *
     * @return
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 盐
     *
     * @return
     */
    public String getsalt() {
        return salt;
    }

    /**
     * 盐
     *
     * @param salt
     */
    public void setsalt(String salt) {
        this.salt = salt;
    }

    /**
     * 认证加密的盐
     *
     * @return
     */
    public String getCredentialsSalt() {
        return loginName + salt;
    }

    public PmsOperator() {

    }
}
