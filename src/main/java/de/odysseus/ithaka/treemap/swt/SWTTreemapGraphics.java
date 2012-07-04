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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Transform;

import de.odysseus.ithaka.treemap.TreemapGraphics;

/**
 * SWT treemap graphics.
 */
public class SWTTreemapGraphics implements TreemapGraphics {
	private final GC gc;
	private final Map<Color, org.eclipse.swt.graphics.Color> swtColors =
		new HashMap<Color, org.eclipse.swt.graphics.Color>();

	public SWTTreemapGraphics(GC gc) {
		this.gc = gc;
	}

	org.eclipse.swt.graphics.Color getSWTColor(Color awtColor) {
		org.eclipse.swt.graphics.Color swtColor = swtColors.get(awtColor);
		if (swtColor == null) {
			swtColor = new org.eclipse.swt.graphics.Color(
					gc.getDevice(),
					awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
			swtColors.put(awtColor, swtColor);
		}
		return swtColor;
	}

	@Override
	public void fill(Rectangle rectangle, Color color) {
		gc.setBackground(getSWTColor(color));
		gc.fillRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	@Override
	public void fill(Rectangle rectangle, Color color1, Color color2) {
		Pattern pattern = new Pattern(
				gc.getDevice(),
				rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height,
				getSWTColor(color1), getSWTColor(color2));
		gc.setBackgroundPattern(pattern);
		gc.fillRectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		pattern.dispose();
	}

	@Override
	public void draw(String string, int x, int y, Color color, boolean vertical) {
		Transform transform = null;
		if (vertical) {
			transform = new Transform(gc.getDevice());
			transform.translate(x, y);
			transform.rotate(-90);
			transform.translate(-x, -y);
			gc.setTransform(transform);
		}
		gc.setForeground(getSWTColor(color));
		gc.drawString(string, x, y, true);
		if (vertical) {
			gc.setTransform(null);
			transform.dispose();
		}
	}

	@Override
	public Dimension getDimension(String string) {
		Point point = gc.textExtent(string);
		return new Dimension(point.x, point.y);
	}
	
	@Override
	public void dispose() {
		for (org.eclipse.swt.graphics.Color color : swtColors.values()) {
			color.dispose();
		}
		swtColors.clear();
	}
}
