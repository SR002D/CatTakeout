package com.nwafu.cattakeout.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;   //0失败，1成功
    private String msg;     //返回信息
    private Object data;    //返回数据

    public static Result success(String msg){
        return new Result(1,msg,null);
    }

    public static Result success(){
        return new Result(1,"success",null);
    }

    public static Result success(Object obj){
        return new Result(1,"success",obj);
    }

    public static Result error(String msg){
        return new Result(0,msg,null);
    }

    public static Result error(){
        return new Result(0,"error",null);
    }
}
