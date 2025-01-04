package com.other.app.Model;

public class ChangePasswordRequest {

		private String username;
		 private String oldPassword;
	    private String newPassword;
	    private String retyepassword;
	    
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getOldPassword() {
			return oldPassword;
		}
		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}
		public String getNewPassword() {
			return newPassword;
		}
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
		public String getRetyepassword() {
			return retyepassword;
		}
		public void setRetyepassword(String retyepassword) {
			this.retyepassword = retyepassword;
		}
		public ChangePasswordRequest(String username, String oldPassword, String newPassword, String retyepassword) {
			super();
			this.username = username;
			this.oldPassword = oldPassword;
			this.newPassword = newPassword;
			this.retyepassword = retyepassword;
		}
		public ChangePasswordRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
	   
	    
		
	    

}
