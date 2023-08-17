package se.nrm.dina.web.portal;
 
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.primefaces.PrimeFaces;
import static org.primefaces.PrimeFaces.setCurrent;

/**
 *
 * @author idali
 */
public abstract class PrimeFacesMocker extends PrimeFaces {
  private PrimeFacesMocker() {
  }

  private static final Release RELEASE = new Release();

  private static class Release implements Answer<Void> {
    @Override
    public Void answer(InvocationOnMock invocation) throws Throwable {
      setCurrent(null);
      return null;
    }   
  }

  public static PrimeFaces mockPrimeFaces() {
    PrimeFaces primeFaces = Mockito.mock(PrimeFaces.class);
    setCurrent(primeFaces); 
    Ajax ajax = Mockito.mock(Ajax.class);
    when(primeFaces.ajax()).thenReturn(ajax);
    Mockito.doAnswer(RELEASE)
            .when(ajax).update("");  
    return primeFaces;
  } 
}
