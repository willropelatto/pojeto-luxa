package br.com.pofexo.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeamMO is a Querydsl query type for TeamMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeamMO extends EntityPathBase<TeamMO> {

    private static final long serialVersionUID = -168392950L;

    public static final QTeamMO teamMO = new QTeamMO("teamMO");

    public final NumberPath<Double> budget = createNumber("budget", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idcup = createNumber("idcup", Integer.class);

    public final NumberPath<Integer> idUser = createNumber("idUser", Integer.class);

    public final StringPath manager = createString("manager");

    public final StringPath name = createString("name");

    public final SetPath<PlayerMO, QPlayerMO> players = this.<PlayerMO, QPlayerMO>createSet("players", PlayerMO.class, QPlayerMO.class, PathInits.DIRECT2);

    public QTeamMO(String variable) {
        super(TeamMO.class, forVariable(variable));
    }

    public QTeamMO(Path<? extends TeamMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeamMO(PathMetadata metadata) {
        super(TeamMO.class, metadata);
    }

}

