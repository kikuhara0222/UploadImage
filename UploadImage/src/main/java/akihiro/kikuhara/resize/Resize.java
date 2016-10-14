package akihiro.kikuhara.resize;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import net.coobird.thumbnailator.Thumbnails;

public class Resize {
	/**
	 * リサイズ実行メソッド
	 */
	public BufferedImage reSize(InputStream inputStream, int width, int height) throws IOException{
		return Thumbnails.of(inputStream).size(width, height).keepAspectRatio(true).outputQuality(1.0f).asBufferedImage();
	}
}
