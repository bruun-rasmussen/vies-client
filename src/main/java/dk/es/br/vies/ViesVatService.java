package dk.es.br.vies;

import eu.europa.ec.taxud.vies.services.checkvat.CheckVatPortType;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import java.net.URL;
import java.util.ResourceBundle;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.SOAPFaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author      osa
 * @since       10-05-2013
 */
public class ViesVatService {
    private static final Logger LOG = LoggerFactory.getLogger(ViesVatService.class);

    private static final URL WSDL = ViesVatService.class.getClassLoader().getResource("wsdl/vies/checkVatService.wsdl");
    private static final QName SVC_NAME = QName.valueOf("{urn:ec.europa.eu:taxud:vies:services:checkVat}checkVatService");

    private final CheckVatService svc = new CheckVatService(WSDL, SVC_NAME);

    public ViesVatRegistration lookup(String country, String vatNumber) throws ViesVatServiceException {
        CheckVatPortType cv = svc.getCheckVatPort();

        Holder<String> country_ = new Holder(country);
        Holder<String> vatNumber_ = new Holder(vatNumber);
        Holder<XMLGregorianCalendar> date_ = new Holder();
        Holder<Boolean> valid_ = new Holder();
        Holder<String> name_ = new Holder();
        Holder<String> address_ = new Holder();

        try {
            cv.checkVat(country_, vatNumber_, date_, valid_, name_, address_);
        }
        catch (SOAPFaultException ex) {
            SOAPFault fault = ex.getFault();
            String faultKey = fault.getFaultString();
            String faultMessage = ResourceBundle.getBundle(ViesVatService.class.getName()).getString("vies.fault." + faultKey);
            throw new ViesVatServiceException(faultKey, country + "-" + vatNumber + ": " + faultMessage);
        }
        catch (WebServiceException ex) {
            LOG.error("{}-{} lookup failed", country, vatNumber, ex);
            throw new ViesVatServiceException("WebServiceException", country + "-" + vatNumber + ": " + ex.getMessage());
        }

        if (!valid_.value)
            return null;

        ViesVatRegistration res = new ViesVatRegistration();

        res.setCountry(country_.value);
        res.setVatNumber(vatNumber_.value);
        res.setRequestDate(date_.value.toGregorianCalendar().getTime());
        res.setName(name_.value);
        res.setAddress(address_.value);
        LOG.info("{}-{} : {}", country, vatNumber, res);

        return res;
    }
}
