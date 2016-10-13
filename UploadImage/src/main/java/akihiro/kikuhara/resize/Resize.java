package akihiro.kikuhara.resize;

import java.awt.image.BufferedImage;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class Resize {
	/**
	 * リサイズ実行メソッド
	 */
	public BufferedImage reSize(BufferedImage image, int width, int height) throws IOException{
		return Thumbnails.of(image).size(width, height).keepAspectRatio(true).outputQuality(1.0f).asBufferedImage();
	}
}
