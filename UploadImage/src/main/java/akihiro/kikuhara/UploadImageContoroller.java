package akihiro.kikuhara;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import akihiro.kikuhara.resize.Resize;

@Controller
public class UploadImageContoroller {

	@Autowired
	private Properties properties;

	// indexページに遷移する
	@RequestMapping("/index")
	public String index(@RequestParam(defaultValue = "") String msg, Model model) {
		model.addAttribute("msg", msg);
		return "index";
	}

	@RequestMapping("/resize")
	public String reSize(@RequestParam MultipartFile uploadImg,RedirectAttributes redirectAttributes, Model model) {
		String inputPath = properties.getInputDir();
		String outputPath = properties.getOutPath();

		BufferedImage bufImg = null;
				
		File file = new File(inputPath + uploadImg.getOriginalFilename());
		String outPutName = null;

		try {
			Resize res = new Resize();

			bufImg = res.reSize(ImageIO.read(file), 300, 300);
			outPutName = outputPath + "resize_" + file.getName();
			ImageIO.write(bufImg, "JPG", new File(outPutName));

		} catch (Exception e) {
			System.out.println("================");
			e.printStackTrace();
			redirectAttributes.addAttribute("msg", "エラーが発生しました");
			return "redirect:/index";
		}
		
		model.addAttribute("filePath","resize_" + file.getName());
		return "newimage";
	}
}
