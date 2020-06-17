package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.User;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class UserDto implements Serializable {

		 private int uid;
		 private int administrationId;
	     private String tempistrationId;
	     private String organizationName;
	     private String upwd;
	   	 @NotBlank(message = "用户名不能为空")
	     private String uname;
	     private String roleIds;
	     private String[] rId;
	     private int nowPage;
	     private int pageSize;

	     public UserDto() {
	    	 super();
	     }

	public UserDto(int uid, int administrationId, String tempistrationId, String organizationName, String upwd, String uname, String roleIds, String[] rId, int nowPage, int pageSize) {
		this.uid = uid;
		this.administrationId = administrationId;
		this.tempistrationId = tempistrationId;
		this.organizationName = organizationName;
		this.upwd = upwd;
		this.uname = uname;
		this.roleIds = roleIds;
		this.rId = rId;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
	}

	public UserDto(User u ) {
		this.uid =u.getUid();
		this.administrationId = u.getAdministrationId();
		this.upwd = u.getUpwd();
		this.uname = u.getUname();
		if(u.getThisOrgan()!=null){
			this.organizationName = u.getThisOrgan().getOrganizationName();
		}else{
			this.organizationName ="无";
		}
	}

	public int getUid() {
			return uid;
		}

		public void setUid(int uid) {
			this.uid = uid;
		}



		public String getUpwd() {
			return upwd;
		}

		public void setUpwd(String upwd) {
			this.upwd = upwd;
		}

		public int getNowPage() {
			return nowPage;
		}

		public void setNowPage(int nowPage) {
			this.nowPage = nowPage;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getAdministrationId() {
		return administrationId;
	}

	public void setAdministrationId(int administrationId) {
		this.administrationId = administrationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getTempistrationId() {
		return tempistrationId;
	}

	public void setTempistrationId(String tempistrationId) {
		this.tempistrationId = tempistrationId;
	}

	public String[] getrId() {
		return rId;
	}

	public void setrId(String[] rId) {
		this.rId = rId;
	}
}
