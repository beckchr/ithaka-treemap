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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import de.odysseus.ithaka.treemap.TreemapGraphics;

/**
 * AWT treemap graphics.
 */
public class AWTTreemapGraphics implements TreemapGraphics {
	private final Graphics2D graphics;

	public AWTTreemapGraphics(Graphics2D graphics) {
		super();

		this.graphics = graphics;
	}

	@Override
	public void dispose() {
		graphics.dispose();
	}

	@Override
	public void draw(String text, int x, int y, Color color, boolean vertical) {		// top left corner
		AffineTransform oldTransform = null;
		if (vertical) {
			oldTransform = graphics.getTransform();
			AffineTransform newTransform = new AffineTransform();
			newTransform.translate(x, y);
			newTransform.rotate(-Math.PI/2);
			newTransform.translate(-x, -y);
			graphics.transform(newTransform);
		}
		graphics.setColor(color);
		FontMetrics metrics = graphics.getFontMetrics();
		graphics.drawString(text, x, y + metrics.getAscent() + metrics.getLeading());	// baseline y
//		graphics.drawGlyphVector(new StandardGlyphVector(graphics.getFont(), text, graphics.getFontRenderContext()), x, y + metrics.getAscent() + metrics.getLeading());	// baseline y
		if (vertical) {
			graphics.setTransform(oldTransform);
		}
	}

	@Override
	public void fill(Rectangle rectangle, Color color) {
		graphics.setPaint(color);
		graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	@Override
	public void fill(Rectangle rectangle, Color color1, Color color2) {
		GradientPaint gradient = new GradientPaint(rectangle.x, rectangle.y, color1, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1, color2);
		graphics.setPaint(gradient);
		graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	@Override
	public Dimension getDimension(String text) {
		FontMetrics metrics = graphics.getFontMetrics();
		return new Dimension(metrics.stringWidth(text), metrics.getHeight());
	}
}
