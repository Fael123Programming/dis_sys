package pt.ipb.dsys.bridge.service;

import org.springframework.web.client.RestTemplate;
import pt.ipb.dsys.bridge.dto.SchoolDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SummaryService {
    private final RestTemplate template;

    public SummaryService() {
        template = new RestTemplate();
    }

    public List<SchoolDto> getSchoolList() {
        SchoolDto[] schoolList = template.getForObject(
                "https://sumarios.ipb.pt/rest/sa/escolas/list", SchoolDto[].class);
        return schoolList == null ? new ArrayList<>() : Arrays.asList(schoolList);
    }
}
