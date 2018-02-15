package br.com.model.bean;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotificationMO is a Querydsl query type for NotificationMO
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNotificationMO extends EntityPathBase<NotificationMO> {

    private static final long serialVersionUID = 465528923L;

    public static final QNotificationMO notificationMO = new QNotificationMO("notificationMO");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath notification = createString("notification");

    public final StringPath playerName = createString("playerName");

    public final BooleanPath read = createBoolean("read");

    public final NumberPath<Integer> teamId = createNumber("teamId", Integer.class);

    public QNotificationMO(String variable) {
        super(NotificationMO.class, forVariable(variable));
    }

    public QNotificationMO(Path<? extends NotificationMO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotificationMO(PathMetadata metadata) {
        super(NotificationMO.class, metadata);
    }

}

