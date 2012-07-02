package de.odysseus.ithaka.treemap.sample;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SampleNode {
	private String value;
	private Color color;
	private int size;
	private List<SampleNode> children;

	public SampleNode(String value, Color color, int size) {
		this.value = value;
		this.color = color;
		this.size = size;
		this.children = new ArrayList<SampleNode>();
	}
	
	public String getValue() {
		return value;
	}

	public Color getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	public List<SampleNode> getChildren() {
		return children;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
