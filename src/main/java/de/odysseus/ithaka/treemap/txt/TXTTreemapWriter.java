package de.odysseus.ithaka.treemap.txt;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import de.odysseus.ithaka.treemap.Treemap;
import de.odysseus.ithaka.treemap.TreemapRenderer;
import de.odysseus.ithaka.treemap.TreemapWriter;

public class TXTTreemapWriter implements TreemapWriter {
	private final TreemapRenderer renderer;
	private final String charset;
	private final String lineSeparator;

	public TXTTreemapWriter(TreemapRenderer renderer) {
		this(renderer, "ISO-8859-1");
	}

	public TXTTreemapWriter(TreemapRenderer renderer, String charset) {
		this(renderer, charset, System.getProperty("line.separator"));
	}

	public TXTTreemapWriter(TreemapRenderer renderer, String charset, String lineSeparator) {
		this.renderer = renderer;
		this.lineSeparator = lineSeparator;
		this.charset = charset;
	}

	@Override
	public void write(Treemap layout, OutputStream output) throws IOException {
		TXTTreemapGraphics graphics = new TXTTreemapGraphics(layout.getWidth(), layout.getHeight());
		renderer.render(graphics, layout);
		graphics.append(new OutputStreamWriter(output, charset), lineSeparator);
		graphics.dispose();
	}
}
