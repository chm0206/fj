package com.chm.fj.entity;

/**
 * 用户组信息
 * @author 陈焕明
 * @date 2018/05/08 14:53
 *
 */
public class GroupInfo {
	/*** 主键 */
	private String gID;
	/*** 用户组名称 */
	private String gName;
	/*** 父级用户组ID */
	private String parentID;
	/*** 用户组状态 */
	private String gStatus;
	/*** 用户组创建时间 */
	private String createDte;
	/*** 用户组编辑时间 */
	private String editDte;
	/*** 创建人 */
	private String operatorID;
	/*** 扩展字段1 */
	private String gRow1;
	/*** 扩展字段2 */
	private String gRow2;
	/*** 扩展字段3 */
	private String gRow3;

	public String getgID() {
		return gID;
	}

	public void setgID(String gID) {
		this.gID = gID == null ? null : gID.trim();
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName == null ? null : gName.trim();
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID == null ? null : parentID.trim();
	}

	public String getgStatus() {
		return gStatus;
	}

	public void setgStatus(String gStatus) {
		this.gStatus = gStatus == null ? null : gStatus.trim();
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

	public String getgRow1() {
		return gRow1;
	}

	public void setgRow1(String gRow1) {
		this.gRow1 = gRow1 == null ? null : gRow1.trim();
	}

	public String getgRow2() {
		return gRow2;
	}

	public void setgRow2(String gRow2) {
		this.gRow2 = gRow2 == null ? null : gRow2.trim();
	}

	public String getgRow3() {
		return gRow3;
	}

	public void setgRow3(String gRow3) {
		this.gRow3 = gRow3 == null ? null : gRow3.trim();
	}
}