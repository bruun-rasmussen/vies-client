package dk.es.br.vies;

import org.junit.Test;

public class LookupIT
{
  @Test
  public void testLookup() throws ViesVatServiceException {
        ViesVatService vl = new ViesVatService();
        ViesVatRegistration resDk = vl.lookup("DK", "26033489");
        ViesVatRegistration resGr = vl.lookup("EL", "054934613");
    }
}
