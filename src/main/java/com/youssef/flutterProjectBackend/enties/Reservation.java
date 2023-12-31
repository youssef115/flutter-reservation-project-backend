package com.youssef.flutterProjectBackend.enties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    private Long time;
    private Long duration;
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(value = { "role","address","password","authorities","username","cin","accountNonExpired","accountNonLocked","credentialsNonExpired","enabled","email" })
    private User userReservation;
    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(value = {"user","category"})
    private Tool toolReservation;
}
