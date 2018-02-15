package br.com.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayerAttributesMO is a Querydsl query type for PlayerAttributesMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayerAttributesMO extends EntityPathBase<PlayerAttributesMO> {

    private static final long serialVersionUID = 1959185480L;

    public static final QPlayerAttributesMO playerAttributesMO = new QPlayerAttributesMO("playerAttributesMO");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final SetPath<PlayerAttributeAssociationMO, QPlayerAttributeAssociationMO> players = this.<PlayerAttributeAssociationMO, QPlayerAttributeAssociationMO>createSet("players", PlayerAttributeAssociationMO.class, QPlayerAttributeAssociationMO.class, PathInits.DIRECT2);

    public QPlayerAttributesMO(String variable) {
        super(PlayerAttributesMO.class, forVariable(variable));
    }

    public QPlayerAttributesMO(Path<? extends PlayerAttributesMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlayerAttributesMO(PathMetadata metadata) {
        super(PlayerAttributesMO.class, metadata);
    }

}

