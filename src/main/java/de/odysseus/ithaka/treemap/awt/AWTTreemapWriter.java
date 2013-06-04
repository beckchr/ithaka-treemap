/*
 * Copyright 2012 Odysseus Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	public void write(Treemap treemap, OutputStream output) throws IOException {
		// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed into integer pixels
		BufferedImage image = new BufferedImage(treemap.getWidth(), treemap.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = image.createGraphics();
		graphics2D.setFont(font);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		AWTTreemapGraphics graphics = new AWTTreemapGraphics(graphics2D);
		renderer.render(graphics, treemap);
		graphics.dispose();
		ImageIO.write(image, format, output);
	}
}