package config;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class ChatVerify implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -4490443980607193791L;

    /**
     * @Fields userId : 用户名
     */
    private String userId;

    /**
     * @Fields userPassword : 密码
     */
    private String userPassword;

    /**
     * @Title: ChatVerify
     * @Description: 创建一个存储用户ID、密码经过MD5加密后的对象
     * @param userId
     *            用户ID
     * @param userPassword
     *            原密码
     */
    public ChatVerify(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = getMd5(userPassword);
    }

    /**
     * @Title: getUserId
     * @Description: 返回用户ID
     * @return: userId 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @Title: getUserPassword
     * @Description: 返回MD5加密后的密码
     * @return: userPassword 加密后的密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @Title: getMd5
     * @Description: 对一个字符串进行MD5加密并返回加密后的串
     * @return: mdPassword 加密后的密码
     * @exception: NoSuchAlgorithmException
     *                 MessageDigest.getInstance("MD5")会抛出该异常.
     */
    private String getMd5(String str) {
        String mdPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 计算md5函数
            md.update(str.getBytes());

            // 保留16位
            mdPassword = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("MD5加密失败：" + e.getMessage());
        }
        return mdPassword;
    }
}