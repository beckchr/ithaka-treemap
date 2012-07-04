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
