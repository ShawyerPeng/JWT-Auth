package com.bitswild.util;

import com.bitswild.bean.User;
import com.bitswild.bean.UserData;

public class UserUtil {
	public static User userDataToUser(UserData userData) {
		User user = new User();
		user.setAccount(userData.getAccount());
		user.setUserId(userData.getUserId());
		user.setRoleId(userData.getRoleId());
		return user;
	}
	
	public static UserData userToUserData(User user) {
		UserData data = new UserData();
		data.setAccount(user.getAccount());
		data.setUserId(user.getUserId());
		data.setRoleId(user.getRoleId());
		return data;
	}
}