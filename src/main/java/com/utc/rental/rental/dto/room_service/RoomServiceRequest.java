package com.utc.rental.rental.dto.room_service;

import java.util.List;

public class RoomServiceRequest {
    private String roomId;
    private List<ServiceSelection> services;

    public static class ServiceSelection {
        private String serviceId;
        private int quantity;
    }
}

