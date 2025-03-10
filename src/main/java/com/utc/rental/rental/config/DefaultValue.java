package com.utc.rental.rental.config;

import java.util.Arrays;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class DefaultValue {

    private List<String> STATUS_ACT = Arrays.asList("ACTIVE", "INACTIVE");

    public static enum StatusActRef {
        ACTIVE,
        INACTIVE,
    }
    
}

