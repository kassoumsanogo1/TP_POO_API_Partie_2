package tp3.ensim.model;

public class AddressForm {
    private String address;
    //private Long id;
    
    // Constructeur par défaut (nécessaire pour la liaison de formulaire)
    public AddressForm() {
    }

    public AddressForm(String address) {
		//super();
		this.address = address;
		//this.id = id;
	}

	// Getters et Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/
}
