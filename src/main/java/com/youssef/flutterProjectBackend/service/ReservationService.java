package com.youssef.flutterProjectBackend.service;

import com.youssef.flutterProjectBackend.enties.Reservation;
import com.youssef.flutterProjectBackend.enties.Tool;
import com.youssef.flutterProjectBackend.handler.ReservationApiException;
import com.youssef.flutterProjectBackend.models.Status;
import com.youssef.flutterProjectBackend.repository.ReservationRepository;
import com.youssef.flutterProjectBackend.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ToolService toolService;
    @Override
    public Reservation createReservation(Reservation reservation) {
        Tool tool=toolService.findToolById(reservation.getToolReservation().getId());
        if(tool!=null){
            tool.setStatus(Status.RESERVED);
            toolService.updateTool(tool);
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationByEmailOrByToolName(String userEmail, Optional<String> toolName) {

        return reservationRepository.findByUserReservationEmailOrToolReservationTitle(userEmail,toolName).orElseThrow(()->new ReservationApiException(HttpStatus.NOT_FOUND,"can't find any reservation with the data provided"));
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
