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

public interface TreemapContentProvider {
	public Object[] getElements(Object input);
	public Object[] getChildren(Object element);
	public boolean hasChildren(Object element);
	public Object getParent(Object element);
	public Object getValue(Object element);
	public int getSize(Object element);
	public boolean hasFrame(Object element);
	public int getChildrenBorder(Object element, int border);
}
