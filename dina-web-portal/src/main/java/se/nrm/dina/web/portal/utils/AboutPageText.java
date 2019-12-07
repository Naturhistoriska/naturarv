package se.nrm.dina.web.portal.utils;

/**
 *
 * @author idali
 */
public class AboutPageText {

  private final String whatIsNaturarvEn = "You can use this search portal to find information about specimens "
          + "deposited at Swedish natural history collections. The portal is presented by DINA, a national collaboration "
          + "centered on digital collections management, to support the dissemination of high quality "
          + "information and increased knowledge of collection holdings.";
  private final String whatIsNaturarvSv = "På Naturarv kan du söka information om föremål i "
          + "flera av Sveriges naturhistoriska samlingar. Webbplatsen har "
          + "tillkommit genom ett nationellt samarbete kring digital samlingshantering. " 
          + "Syftet är att tillhandahålla information av hög kvalitet och därigenom bidra till ökad "
          + "kunskap om de naturhistoriska samlingarna.";
   
  private final String whatIsNaturarvSubEn = "The target audience is primarily collections-based researchers and museum staff, "
          + "but Naturarv was also designed to be a resource for conservation agencies, amateur biologists, and policy-makers. The website "
          + "is hosted at the Swedish Natural History Museum and has been developed with feedback from working groups and staff throughout "
          + "the country with a focus on collections management issues. The searchable content increases as the result of ongoing specimen "
          + "registration at Naturarv collections as well as the addition of new collections. More information on searchable Naturarv "
          + "collections can be found under the heading";
  private final String whatIsNaturarvSubSv = "Naturarv vänder sig i första hand till forskare och personal "
          + "vid museer, men är också tänkt att kunna vara ett viktigt redskap för exempelvis naturvårdare, "
          + "amatörbiologer och beslutsfattare. Webbplatsen drivs från Naturhistoriska riksmuseet i Stockholm och utvecklas "
          + "i dialog med användare vid museer runt om i landet. Det sökbara innehållet utökas kontinuerligt allteftersom "
          + "föremål nyregistreras och nya samlingar läggs till. Under";
             
  private final String partInfrastructureSv = "Arbetet med att bygga upp en digital infrastruktur för Sveriges naturhistoriska "
          + "samlingar tog fart på allvar under 2006 i och med starten av det så kallade DINA-projektet (DINA står för digitalt "
          + "informationssystem för naturhistoriska samlingar). Projektet involverade till en början endast ett fåtal svenska aktörer " 
          + "(Naturhistoriska riksmuseet i Stockholm, Evolutionsmuseet i Uppsala, Göteborgs naturhistoriska museum, Herbarium Göteborg samt "
          + "Biologiska museerna i Lund), men utgörs numera av av något som kan liknas vid ett internationellt konsortium. För svenskt "
          + "vidkommande kvarstår dock målet att skapa en gemensam plattform för landets naturhistoriska data. Mer om DINA finns att läsa "
          + "på projektets egen webbplats,";
  private final String partInfrastructureEn = "Efforts to build a digital infrastructure for Swedish natural history collections "
          + "were initiated under 2006 and the start of the so-called DINA project (DINA stands for ”digital information system for natural "
          + "history collections”). Project membership first included only Swedish institutions (Swedish Museum of Natural History at Stockholm, " 
          + "Evolution Museum at Uppsala, Natural History Museum at Gothenburg, Gothenburg Herbarium, and Biological Museums at Lund) " 
          + "although this group has expanded with the addition of international consortium partners. For Swedish members, the objective "
          + "is to establish a nationally implemented platform for natural history collections data. More about DINA can be " 
          + "found on the project website, ";
  
  private final String partInfrastructureSubEn = "Naturarv is intended to eventually contain information from all Swedish institutions "
          + "that are DINA-project members. The search portal is, therefore, a central part of of the international infrastructure. Under the heading ";
  private final String partInfrastructureSubSv = "Förhoppningen är att Naturarv så småningom ska innehålla information från alla svenska "
          + "institutioner som är delaktiga i DINA-projektet. Sökportalen är därför en central del av den nationella infrastrukturen. Under ";
  
  private final String developmentTextSv = "Den tekniska utvecklingen av komponenterna bakom Naturarv sker inom ramen för DINA-projektet i "
          + "nära samarbete med utvecklare i bland annat övriga Norden, Estland, USA och Kanada. För all programvara som produceras gäller principen om "
          + "öppen källkod. En stor del av utvecklingen sker inom och kring programvaran Specify (läs mer om programmet på ";
  private final String developmentTextEn = "The development of Naturarv components has taken place within the larger context of DINA project "
          + "collaboration and distributed development by teams in Scandinavia, Estonia, USA, and Canada. Software produced as part of this collaboration " 
          + "is distributed as open-source code. A major proportion of development in this group centers around the Specify 6 collections " 
          + "management software clients and database schema (read more about this program at ";
  
  private final String financialSupportSv = "DINA-projektet stöds av de samarbetande institutionerna, Svenska Artprojektet, SLU och av Svenska LifeWatch.";
  private final String financialSupportEn = "The DINA project is supported by participating institutions and project funding by the Swedish "
          + "Species Service (SLU ArtDatabanken, Svenska Artprojektet) and Swedish LifeWatch.";
  
  private static AboutPageText instance = null;

  public static synchronized AboutPageText getInstance() {
    if (instance == null) {
      instance = new AboutPageText();
    }
    return instance;
  }
  
  public String getWhatIsNaturarv(boolean isSwedish) {
    return isSwedish ? whatIsNaturarvSv : whatIsNaturarvEn;
  }
  
  public String getWhatIsNaturarvSub(boolean isSwedish) {
    return isSwedish ? whatIsNaturarvSubSv : whatIsNaturarvSubEn;
  }
  
  public String getInfrastrcutureText(boolean isSwedish) {
    return isSwedish ? partInfrastructureSv : partInfrastructureEn;
  }
  
  public String getInfrastrcutureSubText(boolean isSwedish) {
    return isSwedish ? partInfrastructureSubSv : partInfrastructureSubEn;
  }
  
  
  public String getDevelopmentText(boolean isSwedish) {
    return isSwedish ? developmentTextSv : developmentTextEn;
  }
  
  public String getFinancialSupportText(boolean isSwedish) {
    return isSwedish ? financialSupportSv : financialSupportEn;
  }
  
}
