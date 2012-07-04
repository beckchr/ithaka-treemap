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
