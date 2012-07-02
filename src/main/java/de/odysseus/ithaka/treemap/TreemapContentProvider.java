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
