package de.odysseus.ithaka.treemap.awt;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import de.odysseus.ithaka.treemap.TreemapWriter;
import de.odysseus.ithaka.treemap.Treemap;
import de.odysseus.ithaka.treemap.TreemapRenderer;

public class AWTTreemapWriter implements TreemapWriter {
	private final TreemapRenderer renderer;
	private final String format;
	private final Font font;

	public AWTTreemapWriter(TreemapRenderer renderer, String format, Font font) {
		this.renderer = renderer;
		this.format = format;
		this.font = font;
	}

	@Override
	public void write(Treemap layout, OutputStream output) throws IOException {
		// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed into integer pixels
		BufferedImage image = new BufferedImage(layout.getWidth(), layout.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = image.createGraphics();
		graphics2D.setFont(font);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		AWTTreemapGraphics graphics = new AWTTreemapGraphics(graphics2D);
		renderer.render(graphics, layout);
		graphics.dispose();
		ImageIO.write(image, format, output);
	}
}