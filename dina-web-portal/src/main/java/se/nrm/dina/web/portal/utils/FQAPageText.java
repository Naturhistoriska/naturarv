/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.web.portal.utils;

/**
 *
 * @author idali
 */
public class FQAPageText {
  
  private final String whatIsNaturarvEn = "Naturarv is a website where you can search databases for "
          + "information on biological and geological specimens from many geographic regions that are housed "
          + "at several of Sweden's natural history collections.";  
  private final String whatIsNaturarvSv = "Naturarv är en webbplats där du kan söka efter information "
          + "om föremål i flera av Sveriges  naturhistoriska samlingar. Här hittar du information om biologiska "
          + "och geologiska föremål från olika delar av världen."; 
  
  private final String whoIsRespones1En = "The majority of Naturarv content published here is provided by "
          + "Swedish public institutions. Under ";
  private final String whoIsRespones1Sv = "Merparten av innehållet som publiceras på Naturarv "
          + "tillhandahålls av svenska offentliga institutioner. Under ";
  
  private final String whoIsRespones2En = " one can read about the institutions that have contributed data "
          + "to the Naturarv. The Swedish Museum of Natural History, Stockholm is responsible for website development "
          + "and maintenance. Your questions and feedback on the site are welcomed; contact details are found under ";
  private final String whoIsRespones2Sv = "kan du läsa om vilka som bidrar med innehåll. För utveckling "
          + "och underhåll av själva webbplatsen svarar Naturhistoriska riksmuseet i Stockholm. Hör gärna av dig med "
          + "frågor eller synpunkter. Kontaktuppgifter hittar du under ";
  
  private final String whatInformantion1En = "Naturarv presents information from a wide range of natural history "
          + "collection objects (incl. fossil plants and animals, tissue and DNA samples). Several Naturarv records are "
          + "indirectly linked to physical objects housed at Swedish collections. These records are organized in Naturarv "
          + "collections to manage data relevant for collections management and research, for example, information compiled "
          + "as results from an inventory.";
  private final String whatInformantion1Sv = "Naturarv presenterar information från naturhistoriska samlingar av "
          + "olika slag (fossila växter och djur, vävnads- och DNA-prover, med mera). Några samlingar saknar direkt koppling "
          + "till fysiska föremål. Sådana icke-fysiska samlingar används framförallt till att hantera observationsdata, "
          + "exempelvis uppgifter som samlats in i samband med en inventering.";
  
  
  private final String whatInformantion2En = "Only a proportion of the total collections are represented by records "
          + "published in Naturarv. Which information categories that are shown is steered based on the wishes of users, some "
          + "of which are technically possible, and some based on legal restrictions (for example, we do not publish personal "
          + "information). For the time being, there is for example information on collecting events and taxonomic classification "
          + "of specimens.";
  
  private final String whatInformantion2Sv = "På Naturarv publiceras bara en del av innehållet i den nationella "
          + "samlingsdatabasen. Vilka informationskategorier som visas styrs dels av önskemål från användare, dels av vad "
          + "som är tekniskt möjligt, och dels av vad lagen tillåter (till exempel publicerar vi inte vissa personuppgifter). "
          + "För närvarande finns exempelvis information som rör insamlingstillfället och föremålets klassificering.";
  
  private final String whatInformantion3En = "How information is handled may differ among collections. For example, a single "
          + "geographic locality can have alternative characterizions (e.g., represented by several names or with different coordinate "
          + "precision or accuracy) or be listed under different political regions in national databases. Naturarv does not show all "
          + "original record content so the lack of expected search results does not necessarily indicate whether information is missing "
          + "in source collection databases.";
  private final String whatInformantion3Sv = "Hur information hanteras kan ibland skilja sig mellan samlingar. "
          + "Till exempel kan och samma geografiska plats anges på olika sätt eller läggas in på olika ställen i den nationella "
          + "samlingsdatabasen. Eftersom Naturarv inte visar allt innehåll behöver avsaknad av en uppgift inte nödvändigtvis " 
          + "innebära att informationen saknas i samlingsdatabasen.";
  
  private final String whatSource1En = "Information in Naturarv is compiled from the national collections database. Each institution is "
          + "responsible for data from its own collections. Under";
  private final String whatSource1Sv = "Informationen på Naturarv hämtas från den nationella samlingsdatabasen. Varje institution ansvarar "
          + "själv för data som rör de egna samlingarna. Under ";
  
  private final String whatSource2En = ", one can find a list of institutions represented in Naturarv and the collections for which they "
          + "are responsible.";
  private final String whatSource2Sv  = "kan du se alla institutioner som finns på Naturarv samt vilka samlingar de ansvarar för.";
       
  private final String trustEn = "The objective is to provide data via Naturarv that is informative, correct and up-to-date. Because we do not have "
          + "the possibility to validate the accuracy of the compiled records, the user should be aware that incorrect data may be obtained from Naturarv "
          + "search results. Naturarv is updated, however, every day with the latest information from the national collections database.";
  private final String trustSv = "Målet är att alla uppgifter som publiceras på Naturarv ska vara informativa, korrekta och aktuella. "
          + "Eftersom vi inte har möjlighet att kontrollera varje uppgift så kan felaktigheter ibland förekomma. Naturarv uppdateras varje dygn "
          + "med den senaste informationen från den nationella samlingsdatabasen.";
  
  private final String howOftenEn = "The latest information is imported once daily from the national collections database. Updates are usually performed at "
          + "night, Swedish time.";
  private final String howOftenSv = "Den senaste informationen hämtas en gång per dygn från den nationella samlingsdatabasen. Uppdateringen sker "
          + "vanligtvis på natten, svensk tid.";
  
  private final String howContactEn = "Our contact information is found under";
  private final String howContactSv = "Våra kontaktuppgifter hittar du under";
  
  private static FQAPageText instance = null;

  public static synchronized FQAPageText getInstance() {
    if (instance == null) {
      instance = new FQAPageText();
    }
    return instance;
  }
  
  public String getWhatIsNaturarvText(boolean isSwedish) {
    return isSwedish ? whatIsNaturarvSv : whatIsNaturarvEn;
  }
  
  public String getWhoIsResponsible1(boolean isSwedish) {
    return isSwedish ? whoIsRespones1Sv : whoIsRespones1En; 
  }

  public String getWhoIsResponsible2(boolean isSwedish) {
    return isSwedish ? whoIsRespones2Sv : whoIsRespones2En;
  }

  public String getWhatInformation1(boolean isSwedish) {
    return isSwedish ? whatInformantion1Sv : whatInformantion1En;
  }

  public String getWhatInformation2(boolean isSwedish) {
    return isSwedish ? whatInformantion2Sv : whatInformantion2En;
  }

  public String getWhatInformation3(boolean isSwedish) {
    return isSwedish ? whatInformantion3Sv : whatInformantion3En;
  }

  public String getWhatSource1(boolean isSwedish) {
    return isSwedish ? whatSource1Sv : whatSource1En;
  }

  public String getWhatSource2(boolean isSwedish) {
    return isSwedish ? whatSource2Sv : whatSource2En;
  }

  public String getTrust(boolean isSwedish) {
    return isSwedish ? trustSv : trustEn;
  }

  public String getHowOfter(boolean isSwedish) {
    return isSwedish ? howOftenSv : howOftenEn;
  }

  public String getHowContact(boolean isSwedish) {
    return isSwedish ? howContactSv : howContactEn;
  }
}
