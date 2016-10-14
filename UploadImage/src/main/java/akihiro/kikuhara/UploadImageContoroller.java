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

	// リサイズ処理
	@RequestMapping("/resize")
	public String reSize(@RequestParam MultipartFile uploadImg, RedirectAttributes redirectAttributes, Model model) {
		String outputPath = properties.getOutPath();
		BufferedImage bufImg = null;
		String outPutName = null;

		try {
			Resize res = new Resize();
			bufImg = res.reSize(uploadImg.getInputStream(), 300, 300);
			outPutName = outputPath + "resize_image.jpg";
			ImageIO.write(bufImg, "JPG", new File(outPutName));
			// Eclipseが置き換える間処理を止める
			//Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addAttribute("msg", "エラーが発生しました");
			return "redirect:/index";
		}
		model.addAttribute("filePath", "resize_Image.jpg");
		return "newimage";

	}
}
