package org.example.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author GYF
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result{

    private Integer code;
    private String message;
    private Object data;


    public Result(ResultCode code,Object data) {
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }
}
