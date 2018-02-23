package br.com.pofexo.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayerAttributeAssociationMO is a Querydsl query type for PlayerAttributeAssociationMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayerAttributeAssociationMO extends EntityPathBase<PlayerAttributeAssociationMO> {

    private static final long serialVersionUID = -1125472013L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayerAttributeAssociationMO playerAttributeAssociationMO = new QPlayerAttributeAssociationMO("playerAttributeAssociationMO");

    public final QPlayerAttributesMO attribute;

    public final QPlayerMO player;

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QPlayerAttributeAssociationMO(String variable) {
        this(PlayerAttributeAssociationMO.class, forVariable(variable), INITS);
    }

    public QPlayerAttributeAssociationMO(Path<? extends PlayerAttributeAssociationMO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayerAttributeAssociationMO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayerAttributeAssociationMO(PathMetadata metadata, PathInits inits) {
        this(PlayerAttributeAssociationMO.class, metadata, inits);
    }

    public QPlayerAttributeAssociationMO(Class<? extends PlayerAttributeAssociationMO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attribute = inits.isInitialized("attribute") ? new QPlayerAttributesMO(forProperty("attribute")) : null;
        this.player = inits.isInitialized("player") ? new QPlayerMO(forProperty("player"), inits.get("player")) : null;
    }

}

