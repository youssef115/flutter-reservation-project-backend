package com.youssef.flutterProjectBackend.controller;

import com.youssef.flutterProjectBackend.enties.Reservation;
import com.youssef.flutterProjectBackend.models.ResponseModel;
import com.youssef.flutterProjectBackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseModel<Reservation> addReservation(@RequestBody Reservation reservation){
        return new ResponseModel<>(HttpStatus.OK.value(), "new Reservation is added successfully",reservationService.createReservation(reservation));
    }
    @GetMapping("/query")
    public ResponseModel<List<Reservation>> getReservationByUserEmailOrToolTitle(@RequestParam String email,@RequestParam String toolTitle){
        return new ResponseModel<>(HttpStatus.OK.value(), "all the reservation based on "+email+" "+toolTitle,reservationService.getReservationByEmailOrByToolName(email,toolTitle));
    }

    @PutMapping
    public ResponseModel<Reservation> updateReservation(@RequestBody Reservation reservation){
        return new ResponseModel<>(HttpStatus.OK.value(), "the reservation is updated",reservationService.updateReservation(reservation));
    }
}
