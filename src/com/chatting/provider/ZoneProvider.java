package com.chatting.provider;

import java.util.ArrayList;
import java.util.List;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class ZoneProvider {
	private static ZoneProvider instance = null;

	public SFSExtension ext;

	private ZoneProvider(SFSExtension extension) {
		ext = extension;
	}

	public static ZoneProvider getInstance(SFSExtension extension) {
		if (instance == null) {
			instance = new ZoneProvider(extension);
		}
		
		return instance;
	}

	public static List<User> getListUser() {
		try {
			return (List<User>) instance.ext.getParentZone().getUserList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<User>();
	}
	
	public static User getUserById(int uid) {
		return (User) instance.ext.getParentZone().getUserById(uid);
	}
	
}
