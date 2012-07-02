package de.odysseus.ithaka.treemap;

public interface TreemapBuilder {
	/**
	 * Perform layout algorithm.
	 * @param content content provider
	 * @param input input element
	 * @param width layout width
	 * @param height layout height
	 * @return
	 */
	public Treemap build(TreemapContentProvider content, Object input, int width, int height);
}