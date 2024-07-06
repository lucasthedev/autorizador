package com.authorizer.service.infrastructure.persistence.mcc;

import com.authorizer.service.domain.mcc.Mcc;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mcc")
public class MccJpaEntity {
    @Id
    private Integer id;
    @Column(name = "mcc_code", nullable = false)
    private String mccCode;
    @Column(name = "description", nullable = false)
    private String description;

    public MccJpaEntity(){}

    public MccJpaEntity(Integer id, String mccCode, String description){
        this.id = id;
        this.mccCode = mccCode;
        this.description = description;
    }

    public static MccJpaEntity from(final Mcc mcc){
        return new MccJpaEntity(mcc.getId(),
                mcc.getMccCode(), mcc.getDescription());
    }

    public Mcc toDomain(){
        return Mcc.with(getId(), getMccCode(), getDescription());
    }

    public static Mcc with(final MccJpaEntity entity){
        return Mcc.with(entity.getId(), entity.getMccCode(), entity.getDescription());
    }

    public Integer getId() {
        return id;
    }

    public String getMccCode() {
        return mccCode;
    }

    public String getDescription() {
        return description;
    }
}
