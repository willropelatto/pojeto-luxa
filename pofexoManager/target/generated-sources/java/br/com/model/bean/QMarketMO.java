package br.com.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMarketMO is a Querydsl query type for MarketMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMarketMO extends EntityPathBase<MarketMO> {

    private static final long serialVersionUID = 416146924L;

    public static final QMarketMO marketMO = new QMarketMO("marketMO");

    public final DateTimePath<java.time.LocalDateTime> closeTime = createDateTime("closeTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> cupId = createNumber("cupId", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QMarketMO(String variable) {
        super(MarketMO.class, forVariable(variable));
    }

    public QMarketMO(Path<? extends MarketMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMarketMO(PathMetadata metadata) {
        super(MarketMO.class, metadata);
    }

}

