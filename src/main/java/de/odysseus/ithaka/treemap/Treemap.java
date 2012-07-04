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
