package de.odysseus.ithaka.treemap;

import java.awt.Rectangle;

public class TreemapCell extends Rectangle {
	private static final long serialVersionUID = 2687983461333122744L;

	private final Rectangle borders;
	private Object[] children;
	private final Object value;
	private Rectangle framed = null;

	public TreemapCell(Rectangle bounds, int border, boolean left, boolean top, Object[] children, Object value) {
		super(bounds.x, bounds.y, bounds.width - border, bounds.height - border);
		this.children = children;
		this.value = value;

		if (left || top) {
			int lb = left ? border : 0;
			int tb = top ? border : 0;
			this.borders = new Rectangle(bounds.x - lb, bounds.y - tb, bounds.width + lb, bounds.height + tb);
		} else {
			this.borders = null;
		}
	}

	public Rectangle getBorders() {
		return borders;
	}

	public Rectangle getFramed() {
		return framed;
	}

	public void setFramed(Rectangle framed) {
		this.framed = framed;
	}
	
	public Object[] getChildren() {
		return children;
	}
	
	public boolean isLeaf() {
		return children == null || children.length == 0;
	}
	
	public Object getValue() {
		return value;
	}
}
