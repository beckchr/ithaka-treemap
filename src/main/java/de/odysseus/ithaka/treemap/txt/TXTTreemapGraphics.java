package de.odysseus.ithaka.treemap.txt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.Writer;

import de.odysseus.ithaka.treemap.TreemapGraphics;

public class TXTTreemapGraphics implements TreemapGraphics {
	private char[][] chars;
	
	public TXTTreemapGraphics(int width, int height) {
		this.chars = new char[width + 1][height + 1];
	}

	@Override
	public void fill(Rectangle rectangle, Color color) {
		chars[rectangle.x][rectangle.y] = '+';
		chars[rectangle.x + rectangle.width][rectangle.y] = '+';
		chars[rectangle.x][rectangle.y + rectangle.height] = '+';
		chars[rectangle.x + rectangle.width][rectangle.y + rectangle.height] = '+';
		for (int i = 1; i < rectangle.width; i++) {
			chars[rectangle.x + i][rectangle.y] = '-';
			chars[rectangle.x + i][rectangle.y + rectangle.height] = '-';
		}
		for (int i = 1; i < rectangle.height; i++) {
			chars[rectangle.x][rectangle.y + i] = '|';
			chars[rectangle.x + rectangle.width][rectangle.y + i] = '|';
		}
	}

	@Override
	public void fill(Rectangle rectangle, Color color1, Color color2) {
		fill(rectangle, color1);
	}

	@Override
	public void draw(String string, int x, int y, Color color, boolean vertical) {
		if (vertical) {
			for (char c : string.toCharArray()) {
				chars[x+1][1-string.length()+y++] = c;
			}
		} else {
			for (char c : string.toCharArray()) {
				chars[1+x++][y+1] = c;
			}
		}
	}

	@Override
	public Dimension getDimension(String string) {
		return new Dimension(string.length(), 1);
	}

	@Override
	public void dispose() {
		chars = null;
	}
	
	public void append(Writer writer, String lineSeparator) throws IOException {
		for (int y = 0; y < chars[0].length; y++) {
			for (int x = 0; x < chars.length; x++) {
				writer.write(chars[x][y] == 0 ? ' ' : chars[x][y]);
			}
			writer.write(lineSeparator);
		}
		writer.flush();
	}
}
