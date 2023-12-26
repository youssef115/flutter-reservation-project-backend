package com.youssef.flutterProjectBackend.enties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.youssef.flutterProjectBackend.models.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tool {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Category category;
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    private User user;
    private String imageUrl;
    private String smallDescription;
    private double rentPriceParHour;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    @OneToMany(mappedBy = "toolReservation",cascade = CascadeType.MERGE)
    private List<Reservation> reservations;

}
