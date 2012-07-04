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
