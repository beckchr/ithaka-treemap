package de.odysseus.ithaka.treemap.swt;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

import de.odysseus.ithaka.treemap.TreemapWriter;
import de.odysseus.ithaka.treemap.TreemapGraphics;
import de.odysseus.ithaka.treemap.Treemap;
import de.odysseus.ithaka.treemap.TreemapRenderer;

public class SWTTreemapWriter implements TreemapWriter {
	private final TreemapRenderer renderer;
	private final int format;
	private final Font font;

	public SWTTreemapWriter(TreemapRenderer renderer, int format, Font font) {
		this.renderer = renderer;
		this.format = format;
		this.font = font;
	}

	@Override
	public void write(Treemap layout, OutputStream output) throws IOException, SWTException {
		Image image = new Image(Display.getDefault(), layout.getWidth(), layout.getHeight());
		GC gc = new GC(image);
		gc.fillRectangle(image.getBounds());
		gc.setFont(font);
		gc.setAntialias(SWT.OFF);
		gc.setTextAntialias(SWT.ON);
		TreemapGraphics graphics = new SWTTreemapGraphics(gc);
		renderer.render(graphics, layout);
		graphics.dispose();
		gc.dispose();

		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] {image.getImageData()};
		loader.save(output, format);
		image.dispose();
	}
}