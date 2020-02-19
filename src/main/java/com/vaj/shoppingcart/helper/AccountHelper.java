package com.vaj.shoppingcart.helper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AccountHelper {

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

  public static boolean validateEmail(String email) {
    Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    Matcher m = p.matcher(email);

    return !m.find() || !m.group().equals(email);
  }

  public static String generateSecureRandomPassword() {
    String capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowercase = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";
    String symbols = "!@#$%^&*_=+-/.?<>)";
    String values = capitals + lowercase + numbers + symbols;

    Random rndm_method = new Random();

    char[] password = new char[10];

    for (int i = 0; i < 10; i++) {
      password[i] = values.charAt(rndm_method.nextInt(values.length()));
    }

    return password.toString();
  }
}
