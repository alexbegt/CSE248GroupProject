package com.vaj.shoppingcart.helper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class PasswordHelper {

  private static final SecureRandom RAND = new SecureRandom();

  private static final int ITERATIONS = 65536;
  private static final int KEY_LENGTH = 512;
  private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

  public static Optional<String> generateRandomPasswordSalt() {
    return generateRandomPasswordSalt(512);
  }

  public static Optional<String> generateRandomPasswordSalt(final int length) {

    if (length < 1) {
      throw new AssertionError("error in generatePasswordSalt: length must be > 0");
    }

    byte[] salt = new byte[length];
    RAND.nextBytes(salt);

    return Optional.of(Base64.getEncoder().encodeToString(salt));
  }

  public static Optional<String> generateEncryptedPassword(String password, String salt) {
    char[] chars = password.toCharArray();
    byte[] bytes = salt.getBytes();

    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

    Arrays.fill(chars, Character.MIN_VALUE);

    try {
      SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
      byte[] securePassword = fac.generateSecret(spec).getEncoded();
      return Optional.of(Base64.getEncoder().encodeToString(securePassword));

    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
      throw new AssertionError("Error while hashing a password: " + ex.getMessage(), ex);
    } finally {
      spec.clearPassword();
    }
  }

  public static boolean verifyPassword(String password, String key, String salt) {
    Optional<String> optEncrypted = generateEncryptedPassword(password, salt);
    return optEncrypted.map(s -> s.equals(key)).orElse(false);
  }
}
