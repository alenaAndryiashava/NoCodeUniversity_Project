package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EndPoint {
    CREATE_USER("/api/users"),
    DELETE_USER("/api/users/{email}");

    private final String value;
}

