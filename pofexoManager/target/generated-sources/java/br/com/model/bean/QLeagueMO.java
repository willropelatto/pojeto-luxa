package br.com.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeagueMO is a Querydsl query type for LeagueMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLeagueMO extends EntityPathBase<LeagueMO> {

    private static final long serialVersionUID = 1733423903L;

    public static final QLeagueMO leagueMO = new QLeagueMO("leagueMO");

    public final StringPath abbrName = createString("abbrName");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> originalId = createNumber("originalId", Integer.class);

    public final SetPath<PlayerMO, QPlayerMO> players = this.<PlayerMO, QPlayerMO>createSet("players", PlayerMO.class, QPlayerMO.class, PathInits.DIRECT2);

    public QLeagueMO(String variable) {
        super(LeagueMO.class, forVariable(variable));
    }

    public QLeagueMO(Path<? extends LeagueMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLeagueMO(PathMetadata metadata) {
        super(LeagueMO.class, metadata);
    }

}

