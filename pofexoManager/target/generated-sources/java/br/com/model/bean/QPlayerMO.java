package br.com.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayerMO is a Querydsl query type for PlayerMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayerMO extends EntityPathBase<PlayerMO> {

    private static final long serialVersionUID = 2048448337L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayerMO playerMO = new QPlayerMO("playerMO");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final StringPath atkWorkRate = createString("atkWorkRate");

    public final SetPath<PlayerAttributeAssociationMO, QPlayerAttributeAssociationMO> attributes = this.<PlayerAttributeAssociationMO, QPlayerAttributeAssociationMO>createSet("attributes", PlayerAttributeAssociationMO.class, QPlayerAttributeAssociationMO.class, PathInits.DIRECT2);

    public final NumberPath<Integer> baseId = createNumber("baseId", Integer.class);

    public final QBidMO bid;

    public final StringPath clubName = createString("clubName");

    public final StringPath defWorkRate = createString("defWorkRate");

    public final StringPath foot = createString("foot");

    public final StringPath headshotImgUrl = createString("headshotImgUrl");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QLeagueMO league;

    public final StringPath name = createString("name");

    public final StringPath originalId = createString("originalId");

    public final StringPath position = createString("position");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final EnumPath<br.com.model.misc.PlayerStatus> status = createEnum("status", br.com.model.misc.PlayerStatus.class);

    public final QTeamMO team;

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QPlayerMO(String variable) {
        this(PlayerMO.class, forVariable(variable), INITS);
    }

    public QPlayerMO(Path<? extends PlayerMO> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayerMO(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayerMO(PathMetadata metadata, PathInits inits) {
        this(PlayerMO.class, metadata, inits);
    }

    public QPlayerMO(Class<? extends PlayerMO> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bid = inits.isInitialized("bid") ? new QBidMO(forProperty("bid")) : null;
        this.league = inits.isInitialized("league") ? new QLeagueMO(forProperty("league")) : null;
        this.team = inits.isInitialized("team") ? new QTeamMO(forProperty("team")) : null;
    }

}

