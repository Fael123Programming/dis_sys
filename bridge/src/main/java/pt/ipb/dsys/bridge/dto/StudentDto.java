package pt.ipb.dsys.bridge.dto;

public class StudentDto {
    private String name;
    private int number;

    public StudentDto(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
