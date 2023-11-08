package starter.pageObjects;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//@XmlType(propOrder={"id","id_country","alias","last_name","first_name","address1","city"})
public class AddressesObjects {
	    private int id;
	    private int idCustomer;
	    private int idManufacturer;
	    private int idSupplier;
	    private int idWarehouse;
		private int idCountry;
	    private int idState;
	    private String alias;
	    private String company;
	    private String lastName;
	    private String firstName;
	    private String vatNumber;
	    private String address1;
	    private String address2;
	    private String postcode;
	    private String city;
	    private String other;
	    private String phone;
	    private String phoneMobile;
	    private String dni;
	    private String deleted;
	    private String dateAdd;
	    private String dateUpd;

	    @XmlElement(name = "id")
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    @XmlElement(name = "id_country")
	    public int getIdCountry() {
			return idCountry;
		}

		public void setIdCountry(int idCountry) {
			this.idCountry = idCountry;
		}
		
	    @XmlElement(name = "alias")
		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}
		
	    @XmlElement(name = "lastname")
		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

	    @XmlElement(name = "firstname")
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
	    @XmlElement(name = "address1")
		public String getAddress1() {
			return address1;
		}

		public void setAddress1(String address1) {
			this.address1 = address1;
		}

	    @XmlElement(name = "city")
		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}



	    // Add getters and setters for the remaining fields
	}