package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
public class PeRationVO {

    @JsonProperty("MSG")
    private final String msg = "市盈率各段占比率为：";

    @JsonProperty("RESULT")
    private List<JSONObject>result = new ArrayList<>();
}
