package de.odysseus.ithaka.treemap;

import java.io.IOException;
import java.io.OutputStream;

public interface TreemapWriter {
	public void write(Treemap layout, OutputStream output) throws IOException;
}
