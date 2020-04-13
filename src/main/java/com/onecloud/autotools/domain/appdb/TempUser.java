package com.onecloud.autotools.domain.appdb;


/**
 * Temp Class to hold the username and password credentials of a user who details we
 * do not want to store in the file system for it to be exposed.
 *
 * @author sichituk
 *
 */
public class TempUser {

//	public static Configuration tempUserConfig = new ComponentConfiguration(TempUser.class);
//
//	public static final String username = tempUserConfig.getStringValue(PageConstants.URL_USERNAME);
//	public static final String pwd = tempUserConfig.getStringValue(PageConstants.URL_PASSWORD);

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}