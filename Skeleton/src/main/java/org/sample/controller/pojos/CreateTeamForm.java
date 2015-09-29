package org.sample.controller.pojos;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class CreateTeamForm {


    private Long id;
    private Timestamp date;


    
    @NotEmpty(message = "Must not be empty")
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Timestamp getDate(){
    	return date;
    }
    public void setDate(Timestamp date){
    	this.date=date;
    }
}