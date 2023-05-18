package pt.ipb.dsys.bridge.dto;

public class SchoolDto {
    private int code;
    private String name, abbr;

    public SchoolDto(int code, String name, String abbr) {
        this.code = code;
        this.name = name;
        this.abbr = abbr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }
}
