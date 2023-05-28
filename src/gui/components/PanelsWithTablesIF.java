package gui.components;

import java.util.List;

import model.Asset;

// This code defines a Java interface named `PanelsWithTablesIF`. It declares one method
// `convertToStringArray` that takes a `List` of `Asset` objects as input and returns a 2D array of
// `String` objects. This interface can be implemented by any class that needs to convert a list of
// `Asset` objects into a 2D array of `String` objects.
public interface PanelsWithTablesIF {
	String[][] convertToStringArray(List<Asset> dataArrayList);
	
}
