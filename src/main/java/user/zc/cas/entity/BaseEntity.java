package user.zc.cas.entity;

import lombok.Data;
import user.zc.cas.util.SnowFlake;

import java.util.Date;

@Data
public class BaseEntity {
    private Long id;
    private Long createBy;
    private Date createTime;

    public void create(){
        this.id = SnowFlake.getId();
        this.createBy = -1L;
        this.createTime = new Date();
    }
}
