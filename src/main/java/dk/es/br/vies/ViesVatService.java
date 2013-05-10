package dk.es.br.vies;

import eu.europa.ec.taxud.vies.services.checkvat.CheckVatPortType;
import eu.europa.ec.taxud.vies.services.checkvat.CheckVatService;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

/**
 * @author      osa
 * @since       10-05-2013
 * @version     $Id: ViesVatService.java -1 10-05-2013 14:41:58 osa $
 */
public class ViesVatService {
    private final CheckVatService svc = new CheckVatService();

    public ViesVatRegistration lookup(String country, String vatNumber) {
        CheckVatPortType cv = svc.getCheckVatPort();

        Holder<String> country_ = new Holder(country);
        Holder<String> vatNumber_ = new Holder(vatNumber);
        Holder<XMLGregorianCalendar> date_ = new Holder<XMLGregorianCalendar>();
        Holder<Boolean> valid_ = new Holder<Boolean>();
        Holder<String> name_ = new Holder<String>();
        Holder<String> address_ = new Holder<String>();

        cv.checkVat(country_, vatNumber_, date_, valid_, name_, address_);

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

    public static void main(String args[]) {
        ViesVatService vl = new ViesVatService();
        ViesVatRegistration res = vl.lookup("DK", "26033489");
        System.out.println("vl.lookup(\"DK\", \"26033489\") = "+ res);
    }
}
