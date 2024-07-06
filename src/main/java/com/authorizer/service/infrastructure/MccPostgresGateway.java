package com.authorizer.service.infrastructure;

import com.authorizer.service.domain.mcc.Mcc;
import com.authorizer.service.domain.mcc.MccGateway;
import com.authorizer.service.infrastructure.persistence.mcc.MccJpaEntity;
import com.authorizer.service.infrastructure.persistence.mcc.MccRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MccPostgresGateway implements MccGateway {
    private final MccRepository repository;

    public MccPostgresGateway(MccRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Mcc> findAllMcc() {
        List<MccJpaEntity> mccJpaEntities = repository.findAll();
        return mccJpaEntities.stream().map(MccJpaEntity::toDomain).toList();
    }
}
