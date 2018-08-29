package com.chm.fj.entity;
/**
 * 用户信息
 * @author 陈焕明
 * @date 2018/05/08 14:53
 *
 */
public class UserInfo {
	/*** 主键 */
    private String userID;
    /*** 用户账号 */
    private String userAccount;
    /*** 主键 */
    private String userPass;
    /*** 主键 */
    private String createDte;
    /*** 用户头像 */
    private String userIcon;
    /*** 用户状态 */
    private String userStatus;
    /*** 有效期 */
    private String valiDte;
    /*** 创建人/推荐人 */
    private String operatorID;
    /*** 归属 */
    private String ownerID;
    /*** 扩展字段1 */
    private String userRow1;
    /*** 扩展字段2 */
    private String userRow2;
    /*** 扩展字段3 */
    private String userRow3;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID == null ? null : userID.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getCreateDte() {
        return createDte;
    }

    public void setCreateDte(String createDte) {
        this.createDte = createDte == null ? null : createDte.trim();
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public String getValiDte() {
        return valiDte;
    }

    public void setValiDte(String valiDte) {
        this.valiDte = valiDte == null ? null : valiDte.trim();
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID == null ? null : operatorID.trim();
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID == null ? null : ownerID.trim();
    }

    public String getUserRow1() {
        return userRow1;
    }

    public void setUserRow1(String userRow1) {
        this.userRow1 = userRow1 == null ? null : userRow1.trim();
    }

    public String getUserRow2() {
        return userRow2;
    }

    public void setUserRow2(String userRow2) {
        this.userRow2 = userRow2 == null ? null : userRow2.trim();
    }

    public String getUserRow3() {
        return userRow3;
    }

    public void setUserRow3(String userRow3) {
        this.userRow3 = userRow3 == null ? null : userRow3.trim();
    }
}