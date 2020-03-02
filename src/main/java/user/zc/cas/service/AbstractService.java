package user.zc.cas.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import user.zc.cas.entity.BaseEntity;

import java.lang.reflect.Field;
import java.util.List;

public class AbstractService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    protected <T extends BaseEntity> void insert(T t){
        insert(t,collectKeys(t.getClass()));
    }

    protected <T extends BaseEntity> void insert(T t,String ... keys){
        t.create();
//        String sql = "insert into user(id, username,password,name) values(?,?,?,?)";
//        jdbcTemplate.update(sql, t.getId(), user.getUsername(),user.getPassword(),user.getName());

        String sql = "insert into "+t.getClass().getSimpleName().toLowerCase()+concatKeyAndParam(keys);
        jdbcTemplate.update(sql, getParams(t,keys));
    }

    private String paramStr = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
    private String concatKeyAndParam(String[] keys){
        return "("+String.join(",",keys)+") values("+paramStr.substring(0,(keys.length-1)*2)+")";
    }

    private <T extends BaseEntity> Object getParams(T t ,String ... keys){
        Object[] objects = new Object[keys.length];
        for(int i=0;i<keys.length;i++){
            try {
                Field field  = t.getClass().getDeclaredField(keys[i]);
                field.setAccessible(true);
                objects[i] = field.get(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return objects;
    }

    private String[] collectKeys(Class t){
        return (String[])collectKeys(t,Lists.newArrayList()).toArray();
    }
    private  List<String> collectKeys(Class t ,List<String> list){
        if(t==null){
            return list;
        }
        Field[] fields = t.getDeclaredFields();
        for(Field field: fields){
            list.add(field.getName());
        }
        Class superclass = t.getSuperclass();
        return collectKeys(superclass,list);
    }
}
