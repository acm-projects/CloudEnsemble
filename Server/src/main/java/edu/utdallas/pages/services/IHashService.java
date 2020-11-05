package edu.utdallas.pages.services;

public interface IHashService {

    /**
     * Hashes a string
     * @param input string to hash
     * @return hashed string
     */
    String hashString(String input, String salt);

    /**
     * Generates a random salt
     * @return salt
     */
    String generateSalt();

    /**
     * Converts bytes to string
     * @param input string
     * @return bytes
     */
    String bytesToString(byte[] input);

    /**
     * Converts string to bytes
     * @param input bytes
     * @return string
     */
    byte[] stringToBytes(String input);

}
