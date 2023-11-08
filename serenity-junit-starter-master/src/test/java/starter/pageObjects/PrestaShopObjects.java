package starter.pageObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.jetty.client.Origin.Address;

import java.util.List;

@XmlRootElement(name="prestashop")
public class PrestaShopObjects {
	private AddressesObjects address;

    @XmlElement
    public AddressesObjects getAddress() {
        return address;
    }
 
   

    public void setAddress(AddressesObjects address) {
        this.address = address;
    }
    
  //  public void setPrestashop(AddressesObjects addObj) {
 //       this.addresses = addObj;
  //  }
    
	}
	