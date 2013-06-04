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
	public void write(Treemap treemap, OutputStream output) throws IOException, SWTException {
		Image image = new Image(Display.getDefault(), treemap.getWidth(), treemap.getHeight());
		GC gc = new GC(image);
		gc.fillRectangle(image.getBounds());
		gc.setFont(font);
		gc.setAntialias(SWT.OFF);
		gc.setTextAntialias(SWT.ON);
		TreemapGraphics graphics = new SWTTreemapGraphics(gc);
		renderer.render(graphics, treemap);
		graphics.dispose();
		gc.dispose();

		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] {image.getImageData()};
		loader.save(output, format);
		image.dispose();
	}
}