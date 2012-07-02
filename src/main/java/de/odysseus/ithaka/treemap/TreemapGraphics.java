package de.odysseus.ithaka.treemap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 * Graphics interface.
 */
public interface TreemapGraphics {
	/**
	 * Fill rectangle.
	 * @param rectangle
	 * @param color
	 */
	public void fill(Rectangle rectangle, Color color);

	/**
	 * Fill rectangle with 2-color gradient.
	 * @param rectangle
	 * @param color1
	 * @param color2
	 */
	public void fill(Rectangle rectangle, Color color1, Color color2);

	/**
	 * Draw string.
	 * @param string
	 * @param x
	 * @param y
	 * @param color
	 */
	public void draw(String string, int x, int y, Color color, boolean vertical);

	/**
	 * Examine string extents.
	 * @param string
	 * @return dimension
	 */
	public Dimension getDimension(String string);

	/**
	 * Dispose resources (e.g. colors).
	 */
	public void dispose();
}