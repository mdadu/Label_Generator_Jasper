package fdc;
import java.util.Base64;

public class Base64ToZplConverter {

	public static String convert(String base64Image) {
		String zplCode = "";
		try {
			byte[] imageBytes = Base64.getDecoder().decode(base64Image);
			String imageData = bytesToHex(imageBytes);
			zplCode = "^XA^FO0,0^GFA,";
			zplCode += imageData.length() / 2 + ",";
			zplCode += imageData + "^FS^XZ";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zplCode;
	}
	
	private static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
		sb.append(String.format("%02X", b));
		}
		return sb.toString();
	}
}