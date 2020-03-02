package user.zc.cas.util;

import lombok.Data;

@Data
public class APIResponse {
    private boolean success = true;
    private Integer status = 200 ;
    private String msg = "操作成功";
    private Object data;


    public static APIResponse success(Object data){
        return new APIResponse(data);
    }
    public static APIResponse success(Object data,String message){
        return APIResponse.success(data).message(message);
    }
    public static APIResponse failuer(String msg,Integer status){
        return new APIResponse(msg).status(status).success(false);
    }

    public static APIResponse failuer(String msg){
        return new APIResponse(msg).status(500).success(false);
    }

    public  APIResponse success(boolean success){
        this.success  = success;
        return this;
    }

    public   APIResponse message(String message){
        this.msg  = message;
        return this;
    }

    public   APIResponse status(Integer status){
        this.status  = status;
        return this;
    }

    public APIResponse data(Object data){
        this.data  = data;
        return this;
    }



    private APIResponse(Object object){
        if(object instanceof Boolean){
            this.success = (Boolean) object;
        }
        this.data = object;
    }

    private APIResponse(){
    }

    private APIResponse(String msg){
        this.msg = msg;
    }
}
