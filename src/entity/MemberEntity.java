package entity;

import java.time.LocalDate;

public class MemberEntity {
    private String id;
    private String name;
    private LocalDate dob;
    private String address;
    
    public MemberEntity() {
    }

    public MemberEntity(String id, String name, LocalDate dob, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberEntity [id=" + id + ", name=" + name + ", dob=" + dob + ", address=" + address + "]";
    }

    
}
