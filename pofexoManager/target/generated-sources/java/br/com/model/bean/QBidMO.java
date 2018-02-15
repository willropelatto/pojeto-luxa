package br.com.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBidMO is a Querydsl query type for BidMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBidMO extends EntityPathBase<BidMO> {

    private static final long serialVersionUID = 1164631825L;

    public static final QBidMO bidMO = new QBidMO("bidMO");

    public final NumberPath<Double> bidValue = createNumber("bidValue", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Double> originalValue = createNumber("originalValue", Double.class);

    public final EnumPath<br.com.model.misc.BidStatus> status = createEnum("status", br.com.model.misc.BidStatus.class);

    public final NumberPath<Integer> team = createNumber("team", Integer.class);

    public QBidMO(String variable) {
        super(BidMO.class, forVariable(variable));
    }

    public QBidMO(Path<? extends BidMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBidMO(PathMetadata metadata) {
        super(BidMO.class, metadata);
    }

}

