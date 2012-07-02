package de.odysseus.ithaka.treemap;

import java.awt.Color;

public interface TreemapColorProvider {
	public Color getForegroundColor(Object element, Object value);
	public Color getForegroundShadowColor(Object element, Object value);
	public Color getBackgroundColor(Object element, Object value);
	public Color getBackgroundGradientColor(Object element, Object value);
	public Color getHighlightColor(Object element, Object value);
	public Color getBorderColor(Object element, Object value);
}
