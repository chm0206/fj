package com.chm.fj.entity;
/**
 * 权限菜单信息
 * @author 陈焕明
 * @date 2018/05/08 14:53
 *
 */
public class MenuInfo {
	/*** 主键 */
    private String menuID;
    /*** 菜单名称 */
    private String menuName;
    /*** 菜单访问地址 */
    private String menuUrl;
    /*** 父级菜单 */
    private String parentID;
    /*** 菜单序号 */
    private Integer menuRank;
    /*** 菜单状态 */
    private String menuStatus;
    /*** 创建时间 */
    private String createDte;
    /*** 编辑时间 */
    private String editDte;
    /*** 创建人 */
    private String operatorID;
    /*** 扩展字段1 */
    private String menuRow1;
    /*** 扩展字段2 */
    private String menuRow2;
    /*** 扩展字段3 */
    private String menuRow3;

    public String getMenuID() {
        return menuID;
    }

    public void setMenuID(String menuID) {
        this.menuID = menuID == null ? null : menuID.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID == null ? null : parentID.trim();
    }

    public Integer getMenuRank() {
        return menuRank;
    }

    public void setMenuRank(Integer menuRank) {
        this.menuRank = menuRank;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus == null ? null : menuStatus.trim();
    }

    public String getCreateDte() {
        return createDte;
    }

    public void setCreateDte(String createDte) {
        this.createDte = createDte == null ? null : createDte.trim();
    }

    public String getEditDte() {
        return editDte;
    }

    public void setEditDte(String editDte) {
        this.editDte = editDte == null ? null : editDte.trim();
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID == null ? null : operatorID.trim();
    }

    public String getMenuRow1() {
        return menuRow1;
    }

    public void setMenuRow1(String menuRow1) {
        this.menuRow1 = menuRow1 == null ? null : menuRow1.trim();
    }

    public String getMenuRow2() {
        return menuRow2;
    }

    public void setMenuRow2(String menuRow2) {
        this.menuRow2 = menuRow2 == null ? null : menuRow2.trim();
    }

    public String getMenuRow3() {
        return menuRow3;
    }

    public void setMenuRow3(String menuRow3) {
        this.menuRow3 = menuRow3 == null ? null : menuRow3.trim();
    }
}