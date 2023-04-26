package gui.components;

import java.util.List;

import model.Asset;

public interface PanelsWithTablesIF {
	String[][] convertToStringArray(List<Asset> dataArrayList);
	
}
