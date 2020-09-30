/*
 * Hashing helper classs
 * 4/22/20
 */
package edu.utdallas.pages;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Joseph
 */
public class HashManager {

    /**
     * Hashes a string
     * @param input string to hash
     * @return hashed string
     */
    public static String hashString(String input, String salt)
    {
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
    public static String generateSalt() {
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
    public static String bytesToString(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Converts string to bytes
     * @param input bytes
     * @return string
     */
    public static byte[] stringToBytes(String input) {
        return Base64.decodeBase64(input);
    }

    public static void main(String[] args) {
        String salt = generateSalt();
        System.out.println(salt.length());
        System.out.println(hashString("test",salt));
        System.out.println(hashString("test1",generateSalt()));
        System.out.println(hashString("test",generateSalt()));
        System.out.println(hashString("test",salt));
    }

}