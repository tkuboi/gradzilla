package net.tkuboi.gradzilla.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {
  private final DigestUtils digestUtils = new DigestUtils("SHA-1");

  @Override
  public String encode(CharSequence charSequence) {
    return digestUtils.digestAsHex(charSequence.toString());
  }

  @Override
  public boolean matches(CharSequence charSequence, String s) {
    System.out.println(s);
    System.out.println(digestUtils.digestAsHex(charSequence.toString()));
    System.out.println(digestUtils.digestAsHex(charSequence.toString()).equals(s));
    return digestUtils.digestAsHex(charSequence.toString()).equals(s);
  }
}
