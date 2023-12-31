package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    public Reservation createReservation(Reservation reservation);
    public List<Reservation> getReservationByEmailOrByToolName(String userEmail, Optional<String> toolName);

    public Reservation updateReservation(Reservation reservation);


}
