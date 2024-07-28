package dto;

public class FinesDto {
    private static Integer lateDays;

    public FinesDto() {
    }

     public FinesDto(Integer lateDays) {
        this.lateDays = lateDays;
    }

    public static Integer getLateDays() {
        return lateDays;
    }

     public void setLateDays(Integer lateDays) {
        this.lateDays = lateDays;
    }

    @Override
    public String toString() {
        return "FinesDto [lateDays=" + lateDays + "]";
    }



    
}
