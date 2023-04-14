package model;

public class Service extends Workorder{
	
	private Reference reference;
	
	public Service(Asset asset, String title, String description, Reference reference) {
		super(asset, title, "service", description);
		this.reference = reference;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

}
