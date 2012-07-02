package de.odysseus.ithaka.treemap;

public interface TreemapLabelProvider {
	public String getText(Object node, Object value);
	public String getTooltipText(Object node, Object value);
	public int getLabelLevels(Object root);
}
