package com.synergy.bsc.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.synergy.bsc.model.LoginRequest;

/**
 * @author Tarun
 *
 */
@Component
public class PasswordAES256Encryption {
	
	private Logger logger = LoggerFactory.getLogger(PasswordAES256Encryption.class);

	@Value("${login.userName}")
	public String loginId;
	
	@Value("${login.memberCode}")
	public String memberCode;
	
	@Value("${login.secretKey}")
	public String secretKey;
	
	@Value("${login.password}")
	public String password;
	
	
	//This Method is used to encrypt password in AES256 Algorithm
	public String encryptPassword(String password, String key) throws Exception{
		logger.info("Password Encryption Started");	
		byte[] keyByteArray = new Base64().decode(key.getBytes("UTF-8"));
		SecretKeySpec secretkeySpec = new SecretKeySpec(keyByteArray, "AES");
		Cipher cipher = Cipher.getInstance("aes/ecb/pkcs5padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretkeySpec);
		String encrypt = (new Base64()).encodeAsString(cipher.doFinal(password.getBytes()));
		logger.info("Password Encrypted Successfully = {}",encrypt);
		return encrypt;
	}
	
	
	//Get login details from properties file
	public LoginRequest getLoginDetails() throws Exception {
		LoginRequest loginRequest=new LoginRequest();
		loginRequest.setLoginId(loginId);
		loginRequest.setMemberCode(memberCode);
		loginRequest.setPassword(encryptPassword(password, secretKey));
		logger.info("Login Credentials received successfully");
		return loginRequest;
	}
	
	public static File convert(MultipartFile file) {
		File convFile = new File(file.getOriginalFilename());
		try {
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convFile;
	}
}
