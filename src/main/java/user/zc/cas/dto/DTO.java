package user.zc.cas.dto;
import org.springframework.beans.BeanUtils;

public abstract class DTO<T> {
    public T trans(Class<T> clazz){
        T t  = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(this,t);
        return t;
    }
}