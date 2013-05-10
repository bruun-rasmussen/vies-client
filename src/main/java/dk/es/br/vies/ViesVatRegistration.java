package dk.es.br.vies;

import java.util.Date;

/**
 * @author      osa
 * @since       10-05-2013
 * @version     $Id: ViesVatRegistration.java -1 10-05-2013 14:46:04 osa $
 */
public class ViesVatRegistration {
    private String country;
    private String vatNumber;
    private Date requestDate;
    private String name;
    private String address;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ViesVatRegistration{" + "country=" + country + ", vatNumber=" + vatNumber + ", requestDate=" + requestDate + ", name=" + name + ", address=" + address + '}';
    }
}
