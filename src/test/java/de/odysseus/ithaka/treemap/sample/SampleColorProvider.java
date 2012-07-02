package de.odysseus.ithaka.treemap.sample;

import java.awt.Color;

import de.odysseus.ithaka.treemap.TreemapColorProvider;

public class SampleColorProvider implements TreemapColorProvider {
	private final boolean shadow;
	
	public SampleColorProvider() {
		this(true);
	}
	
	public SampleColorProvider(boolean shadow) {
		this.shadow = shadow;
	}
	
	@Override
	public Color getForegroundColor(Object element, Object value) {
		return Color.WHITE;
	}

	@Override
	public Color getForegroundShadowColor(Object element, Object value) {
		return shadow ? Color.GRAY : null;
	}

	@Override
	public Color getBackgroundColor(Object element, Object value) {
		return ((SampleNode) element).getColor();
	}

	@Override
	public Color getBackgroundGradientColor(Object element, Object value) {
		return ((SampleNode) element).getColor().darker();
	}

	@Override
	public Color getHighlightColor(Object element, Object value) {
		return getBackgroundColor(element, value).brighter();
	}

	@Override
	public Color getBorderColor(Object element, Object value) {
		return Color.GRAY;
	}
}
