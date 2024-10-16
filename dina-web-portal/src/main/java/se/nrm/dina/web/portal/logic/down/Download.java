package se.nrm.dina.web.portal.logic.down;

import java.io.ByteArrayInputStream; 
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter; 
import java.text.SimpleDateFormat; 
import java.util.List;  
import javax.enterprise.context.SessionScoped; 
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat; 
import org.apache.commons.csv.CSVPrinter; 
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import se.nrm.dina.web.portal.model.SolrData;

/**
 *
 * @author idali
 */
@Named("download")
@SessionScoped
@Slf4j
public class Download implements Serializable {
    
    private StreamedContent file;  
    private final String mimetype = "text/csv"; 
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public void downloadCsv() {
        
    }

    public void cSVWriter(List<SolrData> data) throws IOException {

        StringWriter sw = new StringWriter();
          
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(CsvHeader.class)
                .build();

        try (final CSVPrinter printer = new CSVPrinter(sw, csvFormat)) {
            data.forEach(d -> {
                try { 
                    printer.printRecord(d.catalogNumber, d.catalogedDate,
                            d.collectionName, d.higherTx, d.txFullName,
                            d.typeStatus, d.determiner, d.synonymAuthor,
                            d.preservation, d.startDate, d.stationFieldNumber,
                            d.locality, d.county, d.state, d.country,
                            d.continent, d.oceanOrSea, 
                            d.getCoordinateString(), d.getCollectors(),
                            d.getRemarksString());
                } catch (IOException ex) {
                    log.error(ex.getMessage());
                }
            });

        }
        
         
        InputStream stream = new ByteArrayInputStream(sw.toString().getBytes());
     
        file = new DefaultStreamedContent(stream, mimetype, "downloadFile.csv");  
    }
    
    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    } 
}
