package de.odysseus.ithaka.treemap.sample;

import de.odysseus.ithaka.treemap.TreemapContentProvider;

public class SampleContentProvider implements TreemapContentProvider {
	private final int border;
	
	public SampleContentProvider() {
		this(1);
	}
	
	public SampleContentProvider(int border) {
		this.border = border;
	}
	
	@Override
	public Object[] getElements(Object input) {
		return new Object[]{ (SampleNode) input };
	}

	@Override
	public Object[] getChildren(Object element) {
		return ((SampleNode) element).getChildren().toArray();
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public Object getValue(Object element) {
		return ((SampleNode) element).getValue();
	}

	@Override
	public int getSize(Object element) {
		return ((SampleNode) element).getSize();
	}

	@Override
	public boolean hasFrame(Object element) {
		return hasChildren(element);
	}

	@Override
	public int getChildrenBorder(Object element, int border) {
		return this.border;
	}
}
