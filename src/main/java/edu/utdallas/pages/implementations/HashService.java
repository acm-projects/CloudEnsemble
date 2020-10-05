/*
 * Hashing service class
 * 4/22/20
 */
package edu.utdallas.pages.implementations;

import edu.utdallas.pages.services.IHashService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service("HashService")
public class HashService implements IHashService {

    /**
     * Hashes a string
     * @param input string to hash
     * @return hashed string
     */
    public String hashString(String input, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(stringToBytes(salt));
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        }
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a random salt
     * @return salt
     */
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return bytesToString(bytes);
    }

    /**
     * Converts bytes to string
     * @param input string
     * @return bytes
     */
    public String bytesToString(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Converts string to bytes
     * @param input bytes
     * @return string
     */
    public byte[] stringToBytes(String input) {
        return Base64.decodeBase64(input);
    }


}