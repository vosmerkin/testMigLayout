package com.example.migLayout.entity;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;




public class Name {
    public Name(String name) {
        this.name = name;
    }

    public Name() {
    }

    //    @Min(value = 1, message = "Id can't be less than 1 or bigger than 999999")
//    @Max(999999)

    private Integer id;

    //    @Column(name="name")
    private String name;


    @Getter
    @Setter
    private Integer carId;


    @Getter
    @Setter
    private Integer salaryId;


//
//    public Name(Integer id, String name, Integer carId, Integer salaryId) {
//        this.id = id;
//        this.name = name;
//        this.carId = carId;
//        this.salaryId = salaryId;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toJson() {

        var ow = new ObjectMapper();

//                .writer().withRootName(this.getClass().getSimpleName()).withDefaultPrettyPrinter();
        String json;
        try {
            json = ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            json = "{}";
            e.printStackTrace();
        }
        return json;
    }


}
