package de.odysseus.ithaka.treemap.demo;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import de.odysseus.ithaka.treemap.SquarifiedTreemapBuilder;
import de.odysseus.ithaka.treemap.Treemap;
import de.odysseus.ithaka.treemap.TreemapRenderer;
import de.odysseus.ithaka.treemap.TreemapWriter;
import de.odysseus.ithaka.treemap.awt.AWTTreemapWriter;
import de.odysseus.ithaka.treemap.sample.SampleColorProvider;
import de.odysseus.ithaka.treemap.sample.SampleContentProvider;
import de.odysseus.ithaka.treemap.sample.SampleLabelProvider;
import de.odysseus.ithaka.treemap.sample.SampleNode;

/**
 * Save sample treemap as <code>~/treemap-awt.png</code>.
 */
public class AWTTreemapWriterDemo {
	public static void main(String[] args) throws IOException {
		SampleNode blue = new SampleNode("blue", Color.blue, 1);
		SampleNode cyan = new SampleNode("cyan", Color.cyan, 1);
		SampleNode gray = new SampleNode("gray", Color.gray, 1);
		SampleNode green = new SampleNode("green", Color.green, 1);
		SampleNode magenta = new SampleNode("magenta", Color.magenta, 4);
		SampleNode orange = new SampleNode("orange", Color.orange, 1);
		SampleNode pink = new SampleNode("pink", Color.pink, 1);
		SampleNode red = new SampleNode("red", Color.red, 2);
		SampleNode yellow = new SampleNode("yellow", Color.yellow, 1);

		blue.getChildren().addAll(Arrays.asList(cyan, gray, green));
		cyan.getChildren().addAll(Arrays.asList(magenta, orange));
		gray.getChildren().addAll(Arrays.asList(pink, red));
		pink.getChildren().add(yellow);
		
		Treemap layout = new SquarifiedTreemapBuilder().build(new SampleContentProvider(), blue, 400, 400);

		TreemapRenderer renderer = new TreemapRenderer(new SampleLabelProvider(), new SampleColorProvider());

		TreemapWriter writer = new AWTTreemapWriter(renderer, "PNG", null);
		OutputStream output = new FileOutputStream(new File(System.getProperty("user.home"), "treemap-awt.png"));
		writer.write(layout, output);
		output.close();

		System.exit(0);
	}
}
