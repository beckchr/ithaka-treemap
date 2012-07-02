package de.odysseus.ithaka.treemap;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.odysseus.ithaka.treemap.sample.SampleContentProvider;
import de.odysseus.ithaka.treemap.sample.SampleNode;

public class SquarifiedTreemapBuilderTest {
	SampleNode blue;
	SampleNode cyan;
	SampleNode gray;
	SampleNode green;
	SampleNode magenta;
	SampleNode orange;
	SampleNode pink;
	SampleNode red;
	SampleNode yellow;

	@Before
	public void before() {
		blue = new SampleNode("blue", Color.blue, 1);
		cyan = new SampleNode("cyan", Color.cyan, 1);
		gray = new SampleNode("gray", Color.gray, 1);
		green = new SampleNode("green", Color.green, 1);
		magenta = new SampleNode("magenta", Color.magenta, 4);
		orange = new SampleNode("orange", Color.orange, 1);
		pink = new SampleNode("pink", Color.pink, 1);
		red = new SampleNode("red", Color.red, 2);
		yellow = new SampleNode("yellow", Color.yellow, 1);
	}
	
	@Test
	public void test() {
		blue.getChildren().addAll(Arrays.asList(cyan, gray, green));
		cyan.getChildren().addAll(Arrays.asList(magenta, orange));
		gray.getChildren().addAll(Arrays.asList(pink, red));
		pink.getChildren().add(yellow);
		
		TreemapBuilder builder = new SquarifiedTreemapBuilder(10, 5);
		Treemap layout = builder.build(new SampleContentProvider(), blue, 100, 100);
		
		Assert.assertEquals(100, layout.getWidth());
		Assert.assertEquals(100, layout.getHeight());
		Assert.assertEquals(blue, layout.getElements()[0]);
		Assert.assertEquals(new Rectangle( 1,  1, 98, 98), layout.getCell(blue).getBounds());
		Assert.assertEquals(new Rectangle(12, 12, 50, 38), layout.getCell(cyan).getBounds());
		Assert.assertEquals(new Rectangle(12, 51, 50, 37), layout.getCell(gray).getBounds());
		Assert.assertEquals(new Rectangle(63, 12, 25, 76), layout.getCell(green).getBounds());
		Assert.assertEquals(new Rectangle(20, 20, 27, 22), layout.getCell(magenta).getBounds());
		Assert.assertEquals(new Rectangle(48, 20,  6, 22), layout.getCell(orange).getBounds());
		Assert.assertEquals(new Rectangle(20, 59, 11, 21), layout.getCell(pink).getBounds());
		Assert.assertEquals(new Rectangle(32, 59, 22, 21), layout.getCell(red).getBounds());
		Assert.assertEquals(new Rectangle(24, 63,  3, 13), layout.getCell(yellow).getBounds());
	}
}
