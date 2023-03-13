/*
 * This file is part of Ks-Project, licensed under the MIT License.
 *
 * Copyright (c) bingzi (BingZi) <lhby233@outlook.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package online.bingzi.usermodule.subModule.userModule.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Base64;

@Converter
public class Encryptor implements AttributeConverter<String, String> {
    // 随机生成的一个32位Key
    private static final byte[] KEY = "gW1mX7oT1yX8zK1dV8kI2bH0nO8kA7mI".getBytes();
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(attribute.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
//        // 实际的解密操作，就是上面的逆向操作
//        try {
//            SecretKeySpec keySpec = new SecretKeySpec(KEY, "AES");
//            Cipher cipher = Cipher.getInstance(ALGORITHM);
//            cipher.init(Cipher.DECRYPT_MODE, keySpec);
//            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(dbData));
//            return new String(decrypted);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        // 禁止从数据库读取时进行解密操作
        return null;
    }
}
