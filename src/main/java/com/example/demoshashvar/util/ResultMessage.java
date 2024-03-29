package com.example.demoshashvar.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResultMessage {
    private Boolean success;
    private Object object;
}
