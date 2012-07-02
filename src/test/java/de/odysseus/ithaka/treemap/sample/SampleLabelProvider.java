package de.odysseus.ithaka.treemap.sample;

import de.odysseus.ithaka.treemap.TreemapLabelProvider;

public class SampleLabelProvider implements TreemapLabelProvider {
	@Override
	public String getText(Object node, Object value) {
		return value == null ? null : value.toString();
	}

	@Override
	public String getTooltipText(Object node, Object value) {
		return null;
	}

	@Override
	public int getLabelLevels(Object root) {
		return Integer.MAX_VALUE;
	}
}
