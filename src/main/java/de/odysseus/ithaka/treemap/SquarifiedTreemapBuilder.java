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
package de.odysseus.ithaka.treemap;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Squarified treemap layouter.
 * This class is not thread-safe. 
 */
public class SquarifiedTreemapBuilder implements TreemapBuilder {
	private final int maxFrame;
	private final int minContentFrameRatio;

	private TreemapContentProvider content;
	private Map<Object, TreemapCell> map;
	private boolean sort = false;

	public SquarifiedTreemapBuilder() {
		this(20, 3);
	}

	public SquarifiedTreemapBuilder(int maxFrame, int minContentFrameRatio) {
		this.maxFrame = maxFrame;
		this.minContentFrameRatio = minContentFrameRatio;
	}

	private Rectangle getFramed(Object node, Rectangle r) {
		int f = getFrameThickness(r);
		Rectangle result = new Rectangle(r.x + f, r.y + f, r.width - f - f, r.height - f - f);
		int totalArea = r.width * r.height;
		int framedArea = result.width * result.height;
		int childrenSize = 0;
		for (Object child : content.getChildren(node)) {
			childrenSize += content.getSize(child);
		}
		float childrenRatio = childrenSize / (float) content.getSize(node);
		if (childrenRatio < framedArea / (float) totalArea) {	// choose a larger f so that (w-2f)(h-2f)/(wh) = childrenRatio
			f = (int) ((r.width + r.height) / 4.0 - Math.sqrt((r.width + r.height) * (r.width + r.height) / 4.0 + r.width * r.height * (childrenRatio - 1)) / 2);
			result = new Rectangle(r.x + f, r.y + f, r.width - f - f, r.height - f - f);
		}
		return result.width > 0 && result.height > 0 ? result : null;
	}

	private int getFrameThickness(Rectangle bounds) {
		int min = Math.min(bounds.width, bounds.height);
		return Math.min(maxFrame, Math.max(3, min / minContentFrameRatio));
	}

	
	@Override
	public Treemap build(TreemapContentProvider content, Object input, int width, int height) {
		this.content = content;
		this.map = new HashMap<Object, TreemapCell>();
		
		Object[] elements = content.getElements(input);
		Rectangle bounds = new Rectangle(width, height);
		if (elements.length > 0) {
			int border = content.getChildrenBorder(input, 0);
			if (border > 0) {
				bounds = new Rectangle(bounds.x + border, bounds.y + border, bounds.width - border, bounds.height - border);
			}
			layout(elements, 0, elements.length - 1, bounds, true, true, border);
		}
		return new Treemap(map, elements, width, height);
	}

	private void layout(Object node, Rectangle bounds, boolean left, boolean top, int border) {
		Object[] children = content.getChildren(node);
		if (sort && children.length > 1) {
			children = children.clone();
			Arrays.sort(children, new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					int w1 = content.getSize(o1);
					int w2 = content.getSize(o2);
					return w1 < w2 ? 1 : w1 > w2 ? -1 : 0;
				}
			});
		}
		TreemapCell cell = new TreemapCell(bounds, Math.max(0, border), left, top, children, content.getValue(node));
		if (cell.isEmpty()) {
			return;
		}
		map.put(node, cell);
		if (children.length > 0) {
			if (content.hasFrame(node)) {
				bounds = getFramed(node, cell);
				if (bounds == null) {
					return;
				}
				cell.setFramed(bounds);
			} else {
				bounds = cell;
			}
			border = content.getChildrenBorder(node, border); // to be applied to children
			if (border > 0) {
				bounds = new Rectangle(bounds.x + border, bounds.y + border, bounds.width - border, bounds.height - border);
			}
			layout(children, 0, children.length - 1, bounds, true, true, border);
		}
	}

	private void layout(Object[] items, int start, int end, Rectangle bounds, boolean left, boolean top, int border) {
		if (start > end) {
			return;
		}

		if (end - start < 2) {
			fixate(items, start, end, bounds, left, top, border);
			return;
		}

		int x = bounds.x, y = bounds.y, w = bounds.width, h = bounds.height;
		double total = size(items, start, end);
		int mid = start;
		double a = content.getSize(items[start]) / total;
		double b = a;
		double qMin = a, qMax = a;

		if (w < h) { // height/width
			double worst = normAspect(h, w, a, b);
			while (mid < end) {
				double q = content.getSize(items[mid+1]) / total;
				qMin = Math.min(qMin, q);
				qMax = Math.max(qMax, q);
				double aspect = Math.max(normAspect(h, w, qMin, b + q), normAspect(h, w, qMax, b + q));
				if (aspect > worst) {
					break;
				}
				mid++;
				b += q;
				worst = aspect;
			}
			int length = (int)Math.round(h * b);
			fixate(items, start, mid, new Rectangle(x, y, w, length), left, top, border);
			layout(items, mid + 1, end, new Rectangle(x, y + length, w, h - length), left, false, border);
		} else { // width/height
			double worst = normAspect(w, h, a, b);
			while (mid < end) {
				double q = content.getSize(items[mid+1]) / total;
				qMin = Math.min(qMin, q);
				qMax = Math.max(qMax, q);
				double aspect = Math.max(normAspect(w, h, qMin, b + q), normAspect(w, h, qMax, b + q));
				if (aspect > worst) {
					break;
				}
				mid++;
				b += q;
				worst = aspect;
			}
			int length = (int)Math.round(w * b);
			fixate(items, start, mid, new Rectangle(x, y, length, h), left, top, border);
			layout(items, mid + 1, end, new Rectangle(x + length, y, w - length, h), false, top, border);
		}
	}

	private void fixate(Object[] nodes, int start, int end, Rectangle bounds, boolean left, boolean top, int border) {
		if (start > end) {
			return;
		}

		boolean vertical = bounds.height > bounds.width;
		int offset = 0;
		double delta = 0;
		double size = size(nodes, start, end);

		for (int i = start; i < end; i++) {
			if (vertical) {
				double exactHeight = bounds.height * content.getSize(nodes[i]) / size;
				int height = (int) Math.round(exactHeight - delta);
				layout(nodes[i], new Rectangle(bounds.x, bounds.y + offset, bounds.width, height), left, top && i == start, border);
				offset += height;
				delta += height - exactHeight;	// abs(dNew) = abs(dOld+h-e) = abs(h-(e-dOld)) <= 0.5 since h = round(e-dOld)
			} else {
				double exactWidth = bounds.width * content.getSize(nodes[i]) / size;
				int width = (int) Math.round(exactWidth - delta);
				layout(nodes[i], new Rectangle(bounds.x + offset, bounds.y, width, bounds.height), left && i == start, top, border);
				offset += width;
				delta += width - exactWidth;
			}
		}

		if (vertical) {
			layout(nodes[end], new Rectangle(bounds.x, bounds.y + offset, bounds.width, bounds.height - offset), left, top && start == end, border);
		} else {
			layout(nodes[end], new Rectangle(bounds.x + offset, bounds.y, bounds.width - offset, bounds.height), left && start == end, top, border);
		}
	}

	private double size(Object[] nodes, int start, int end) {
		int d = 0;
		for (int i = start; i <= end; i++) {
			d += content.getSize(nodes[i]);
		}
		return d;
	}

    private double aspect(double big, double small, double a, double b) {
        return (big * b) / (small * a / b);
    }
    
    private double normAspect(double big, double small, double a, double b) {
        double x = aspect(big, small, a, b);
        return x < 1 ? 1 / x : x;
    }
}
