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
    
    private List<String> TYPE_GENDER = Arrays.asList("M", "F");

    public static enum TypeGenderRef {
        M,
        F,
    }
    
    private List<String> STATUS_ROLE = Arrays.asList("USER", "OWNER", "STAFF");

    public static enum StatusRoleRef {
        USER,
        OWNER,
        STAFF
    }
    
    private List<String> STATUS_SERVICE = Arrays.asList("ACTIVE", "SUSPEND", "STOP");

    public static enum StatusServiceRef {
        ACTIVE,
        SUSPEND,
        STOP
    }
}

