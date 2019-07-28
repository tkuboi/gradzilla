package net.tkuboi.gradzilla.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Md5Hashing {
  public static String getHash(String input) {
    String hash = "";
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(input.getBytes());
      md.update(new Date().toString().getBytes());
      byte[] digest = md.digest();
      hash = DatatypeConverter
        .printHexBinary(digest).toUpperCase();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
