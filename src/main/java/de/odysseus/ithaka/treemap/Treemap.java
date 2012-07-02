package de.odysseus.ithaka.treemap;

import java.io.Serializable;
import java.util.Map;

public class Treemap implements Serializable {
	private static final long serialVersionUID = 6718000493397550399L;

	private Map<Object, TreemapCell> map;
	private Object[] elements;
	private int width;
	private int height;
	
	public Treemap(Map<Object, TreemapCell> map, Object[] elements, int width, int height) {
		this.map = map;
		this.elements = elements;
		this.width = width;
		this.height = height;
	}
	
	public TreemapCell getCell(Object item) {
		return map.get(item);
	}
	
	public Object[] getElements() {
		return elements;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
