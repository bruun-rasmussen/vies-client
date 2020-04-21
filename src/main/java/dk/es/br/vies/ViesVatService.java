package dk.es.br.vies;

import eu.europa.ec.taxud.vies.services.checkvat.CheckVatPortType;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import java.util.ResourceBundle;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * @author      osa
 * @since       10-05-2013
 */
public class ViesVatService {
    private final CheckVatService svc = new CheckVatService();

    public ViesVatRegistration lookup(String country, String vatNumber) throws ViesVatServiceException {
        CheckVatPortType cv = svc.getCheckVatPort();

        Holder<String> country_ = new Holder(country);
        Holder<String> vatNumber_ = new Holder(vatNumber);
        Holder<XMLGregorianCalendar> date_ = new Holder<XMLGregorianCalendar>();
        Holder<Boolean> valid_ = new Holder<Boolean>();
        Holder<String> name_ = new Holder<String>();
        Holder<String> address_ = new Holder<String>();

        try {
            cv.checkVat(country_, vatNumber_, date_, valid_, name_, address_);
        }
        catch (SOAPFaultException ex) {
            SOAPFault fault = ex.getFault();
            String faultKey = fault.getFaultString();
            String faultMessage = ResourceBundle.getBundle(ViesVatService.class.getName()).getString("vies.fault." + faultKey);
            throw new ViesVatServiceException(faultKey, country + "-" + vatNumber + ": " + faultMessage);
        }

        if (!valid_.value)
            return null;

        ViesVatRegistration res = new ViesVatRegistration();

        res.setCountry(country_.value);
        res.setVatNumber(vatNumber_.value);
        res.setRequestDate(date_.value.toGregorianCalendar().getTime());
        res.setName(name_.value);
        res.setAddress(address_.value);

        return res;
    }
}
