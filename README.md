# Ithaka Treemap

[_Ithaka Treemap_](https://github.com/beckchr/ithaka-treemap/) provides a framework for tree maps.

## Features

- API separating layout and rendering
- Squarified treemap layout builder
- Rendering support for AWT and SWT

## Survey

Here's a brief introduction the core interfaces and classes.

![](https://raw.github.com/beckchr/ithaka-treemap/master/Core-API.png)

As a side note, the above image has been rendered from a layout computed by _Ithaka Treemap_.

### TreemapContentProvider Interface

The `TreemapContentProvider` interface is used to describe the tree structure and layout properties.

	Object[] getElements(Object input); // get root elements
	Object[] getChildren(Object element); // get element children
	boolean hasChildren(Object element); // leaf test
	Object getParent(Object element); // get parent element
	Object getValue(Object element); // get an elements's value
	int getSize(Object element); // get size for element (cell size)
	boolean hasFrame(Object element); // layout children with frame
	int getChildrenBorder(Object element, int border); // border around children

An element is visible in the layout if it is a leaf (`hasChildren(element) == false`) or it has a
frame (`hasFrame(element) == true`). Otherwise the element's cell in the layout will be totally
covered by its children.

The values associated with elements (`getValue(element)`) will be stored in the layout so they're
available when rendering the layout to determine labels and colors.

### TreemapBuilder Interface

The content provider is all needed to perform a layout.
A `TreemapBulder` is used to create a treemap (i.e. the layout).

	Treemap build(TreemapContentProvider content, Object input, int width, int height);

### SquarifiedTreemap Class

The `SquarifiedTreemapBuilder` class implements `TreemapBulder`, taking care about keeping the
aspect ratio of cells in the layout close to one.

### TreemapLabelProvider Interface

When rendering a treemap, labels can be placed into cells. The `TreemapLabelProvider` provides
the API to specify those:

	String getText(Object node, Object value); // label to place inside a cell
	String getTooltipText(Object node, Object value); // optional tooltip label
	int getLabelLevels(Object root); // depth to which labels should be shown

### TreemapColorProvider Interface

	Color getForegroundColor(Object element, Object value); // label color
	Color getForegroundShadowColor(Object element, Object value); // optional label shadow color
	Color getBackgroundColor(Object element, Object value); // primary cell color
	Color getBackgroundGradientColor(Object element, Object value); // optional cell gradient color
	Color getHighlightColor(Object element, Object value); // cell selection color
	Color getBorderColor(Object element, Object value); // border color

### TreemapGraphics Interface

The `TreemapGraphics` interface provides all the methods required to actually draw a treemap.
It is implemented by classes `AWTTreemapGraphics` and `SWTTreemapGraphics`. There's usually
no need to implement your own.

### TreemapRenderer Class

Having a `TreemapLabelProvider` and `TreemapColorProvider`, a treemap can be rendered.

	TreemapRenderer(TreemapLabelProvider labelProvider, TreemapColorProvider colorProvider);
	void render(TreemapGraphics graphics, Treemap layout);

### TreemapWriter Interface

The `TreemapWriter` interface is a convenient image creation.

	void write(Treemap layout, OutputStream output) throws IOException;

Again, there are implementations for AWT and SWT, taking a `TreemapRenderer`, image type and label
font at construction time.

## Downloads

Add Maven repository

	<repository>
		<id>ithaka</id>
		<url>http://beckchr.github.com/ithaka-maven/mvnrepo/</url>
	</repository>

as well as dependency

	<dependency>
		<groupId>de.odysseus.ithaka</groupId>
		<artifactId>ithaka-treemap</artifactId>
		<version>0.9</version>
	</dependency>

or manually grab latest JARs [here](http://beckchr.github.com/ithaka-maven/mvnrepo/de/odysseus/ithaka/ithaka-treemap/0.9). 

## License

_Ithaka Treemap_ is available under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).


_(c) 2012 Odysseus Software_