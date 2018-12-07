package br.com.pofexo.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserAppMO is a Querydsl query type for UserAppMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserAppMO extends EntityPathBase<UserAppMO> {

    private static final long serialVersionUID = 432686157L;

    public static final QUserAppMO userAppMO = new QUserAppMO("userAppMO");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath lastname = createString("lastname");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath username = createString("username");

    public QUserAppMO(String variable) {
        super(UserAppMO.class, forVariable(variable));
    }

    public QUserAppMO(Path<? extends UserAppMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAppMO(PathMetadata metadata) {
        super(UserAppMO.class, metadata);
    }

}

