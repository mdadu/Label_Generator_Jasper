package fdc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class App {
	// name and destination of output file e.g. "report.pdf"
	private static String destFileName = "output/report.pdf";

	public static void main(String[] args) throws JRException, IOException, ParseException {

		System.out.println("generating jasper report...");

		JasperReport jasperReport = getJasperReport();
		Map<String, Object> parameters = getParameters();
		JRDataSource dataSource = getDataSource();
//		JRDataSource dataSource = new JRBeanCollectionDataSource(null);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		
		// Set page size to 4x6 inches
        jasperPrint.setPageWidth(4 * 72); // 4 inches converted to points (1 inch = 72 points)
        jasperPrint.setPageHeight(6 * 72); // 6 inches converted to points

		JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
		
//		exportPdfToImage();
		String base64String = ConvertPDFPagesToImages();
		String zplCode = base64ToZPL(base64String);
        System.out.println("ZPL string: " + zplCode);

		System.out.print("\n generated");

	}

	private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
		File template = ResourceUtils.getFile("classpath:ShipmentLabel.jrxml");
		return JasperCompileManager.compileReport(template.getAbsolutePath());
	}

	private static Map<String, Object> getParameters() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("createdBy", "mdadu");
		return parameters;
	}

	private static JRDataSource getDataSource() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(ResourceUtils.getFile("classpath:sample.json")));
		JSONObject jObj = (JSONObject) obj;
        System.out.print(obj);
		System.out.print("\n");
	      
		JSONObject from_address = (JSONObject) jObj.get("from_address");
		String company_name = (String) from_address.get("company_name");
	    String line1_1 = (String) from_address.get("line1");
	    String city_1 = (String) from_address.get("city");
	    String state_province_1 = (String) from_address.get("state_province");
	    String postal_code_1 = (String) from_address.get("postal_code");
	    String sms = (String) from_address.get("sms");
	    String country_code_1 = (String) from_address.get("country_code");
		JSONObject to_address = (JSONObject) jObj.get("to_address");
	    String first_name = (String) to_address.get("first_name");
	    String last_name = (String) to_address.get("last_name");
	    String line1 = (String) to_address.get("line1");
	    String city = (String) to_address.get("city");
	    String state_province = (String) to_address.get("state_province");
	    String postal_code = (String) to_address.get("postal_code");
	    String phone_number = (String) to_address.get("phone_number");
	    String country_code = (String) to_address.get("country_code");
		JSONObject front_door = (JSONObject) jObj.get("front_door");
	    String tracking_number = (String) front_door.get("tracking_number");
		JSONObject client = (JSONObject) front_door.get("client");
	    String qr_code = (String) client.get("qr_code");
	    System.out.println("tracking_number: " + tracking_number);
		List<Labelgenerator> labelgenerator = new LinkedList<Labelgenerator>();

		labelgenerator.add(new Labelgenerator( company_name,line1_1,city_1 ,state_province_1,postal_code_1,sms, country_code_1 ,first_name,last_name,line1,city,state_province, postal_code ,phone_number,country_code,tracking_number, qr_code ));
		return new JRBeanCollectionDataSource(labelgenerator);
	}
	
	public static String ConvertPDFPagesToImages() {
		try {
			String sourceDir = "output/report.pdf"; // Pdf files are read from this folder
			String destinationDir = ""; // converted images from pdf document are saved here
			
			File sourceFile = new File(sourceDir);
			File destinationFile = new File(destinationDir);
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("Folder Created -> "+ destinationFile.getAbsolutePath());
			}
			if (sourceFile.exists()) {
				System.out.println("Images copied to Folder: "+ destinationFile.getName());   

	            FileInputStream fileInputStream = new FileInputStream(sourceFile);
				PDDocument document = PDDocument.load(sourceFile);
				
				// Create PDF renderer
	            PDFRenderer pdfRenderer = new PDFRenderer(document);
	            
	            // Render PDF page to BufferedImage
//	            BufferedImage bufferedImage = pdfRenderer.renderImage(0);
	            
	            // Render the first page of the PDF to a BufferedImage
//	            BufferedImage bufferedImage = pdfRenderer.renderImage(0, 1.0f);
	            
	            // Render the first page of the PDF document as a PNG image 
	            BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(0, 203, ImageType.RGB);

	            // Resize the image to 4x6 inches
	            int width = (int) (4 * 203); // 4 inches converted to points (1 inch = 72 points)
	            int height = (int) (6 * 203); // 6 inches converted to points (1 inch = 72 points)
	            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            resizedImage.getGraphics().drawImage(bufferedImage, 0, 0, width, height, null);

	            // Convert BufferedImage to byte array
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            // Save the resized image as a JPEG file
	            ImageIO.write(resizedImage, "png", byteArrayOutputStream);

	            System.out.println("PDF successfully converted to image.");

	            byte[] byteArray = byteArrayOutputStream.toByteArray();
	            
	            // Convert byte array to Base64 encoded string
	            String base64Image = Base64.getEncoder().encodeToString(byteArray);

	            document.close();
	            fileInputStream.close();

	            return base64Image;
			} else {
				System.err.println(sourceFile.getName() +" File not exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String base64ToZPL(String base64String) throws IOException {

        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64String);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage orginalImage = ImageIO.read(bais);
        ZplConverter zp = new ZplConverter();
        zp.setCompressHex(true);
        zp.setBlacknessLimitPercentage(50);
        String zplBuffer = zp.convertfromImg(orginalImage);

		// Save ZPL code to a file
		Files.write(new File("output/shipment_label.zpl").toPath(), zplBuffer.getBytes());
        
        return zplBuffer;
	}

}