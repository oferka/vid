package org.ok.vid.user.data.content.provider;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CsvReader {

    private final CsvLineReader csvLineReader;

    @Value("classpath:data/users.csv")
    private Resource file;

    public @NotNull List<UserLine> read() {
        List<UserLine> result = new ArrayList<>();
        try {
            Reader reader = new FileReader(file.getFile());
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .withSkipLines(1)  //skip header line
                    .build();
            List<String[]> lines = csvReader.readAll();
            for (String[] line : lines) {
                result.add(csvLineReader.read(line));
            }
            reader.close();
            csvReader.close();
        }
        catch (Exception e) {
            log.error(String.format("Failed to read users from CSV file %s", file.getFilename()), e);
        }
        return result;
    }
}
