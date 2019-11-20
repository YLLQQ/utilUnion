package unit.tool;

import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import java.nio.charset.Charset;

/**
 * @ClassName EncryptUtil
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/11/20 3:26 下午
 */
public class EncryptUtil {

	public static String md5(String string) {
		return Hashing.md5().hashString(string, Charset.forName("UTF-8")).toString();
	}

	public static String encodeWithBase64(String string) {
		String decode = BaseEncoding.base64().encode(string.getBytes());

		return decode;
	}

	public static String decodeWithBase64(String string) {
		byte[] decode = BaseEncoding.base64().decode(string);

		return String.valueOf(decode);
	}

	public static void main(String[] args) {
		String s = EncryptUtil.encodeWithBase64("123456");

		System.out.println(s);

		String s1 = EncryptUtil.decodeWithBase64(s);

		System.out.println(s1);

		String s2 = md5("123456");

		System.out.println(s2);
	}
}
