
package com.mycompany.proyectoapi.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "requested_Data")
public class RequestedData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String iso;
    
    private LocalDate reportDate;
    
    private LocalDateTime requestDateTime;

    public Long getId() {
        return id;
    }

    public String getIso() {
        return iso;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }
    
    


}
