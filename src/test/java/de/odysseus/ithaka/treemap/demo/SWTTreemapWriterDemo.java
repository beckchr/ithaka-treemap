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
package de.odysseus.ithaka.treemap.demo;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.odysseus.ithaka.treemap.SquarifiedTreemapBuilder;
import de.odysseus.ithaka.treemap.Treemap;
import de.odysseus.ithaka.treemap.TreemapRenderer;
import de.odysseus.ithaka.treemap.TreemapWriter;
import de.odysseus.ithaka.treemap.sample.SampleColorProvider;
import de.odysseus.ithaka.treemap.sample.SampleContentProvider;
import de.odysseus.ithaka.treemap.sample.SampleLabelProvider;
import de.odysseus.ithaka.treemap.sample.SampleNode;
import de.odysseus.ithaka.treemap.swt.SWTTreemapWriter;

/**
 * Save sample treemap as <code>~/treemap-swt.png</code>.
 */
public class SWTTreemapWriterDemo {
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

		TreemapWriter writer = new SWTTreemapWriter(renderer, SWT.IMAGE_PNG, null);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		writer.write(layout, output);
		output.close();

        Display display = Display.getDefault();
        Image image = new Image(display, new ByteArrayInputStream(output.toByteArray()));
        Shell shell = new Shell(display);
        shell.setText("SWT Treemap Writer Demo");
        shell.setLayout(new FillLayout());
        Label label = new Label(shell, SWT.NONE);
        label.setImage(image);

        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        image.dispose();
        display.dispose();
        
        System.exit(0);
	}
}
