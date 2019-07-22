package com.mciefova.ataccamatask.connection.data.model.dao;

import com.mciefova.ataccamatask.connection.data.model.entities.ConnectionEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ConnectionEntityDAO extends AbstractDAO {

    public static final String ID_PROPERTY = "id";
    public static final String NAME_PROPERTY = "name";
    public static final String HOST_PROPERTY = "host";
    public static final String PORT_PROPERTY = "port";
    public static final String DATABASE_NAME_PROPERTY = "databaseName";
    public static final String DATABASE_TYPE_PROPERTY = "databaseType";
    public static final String USER_NAME_PROPERTY = "userName";
    public static final String PASSWORD_PROPERTY = "password";

    public List<ConnectionEntity> listAllConnections () {
        CriteriaQuery query = getSession().getCriteriaBuilder().createQuery(ConnectionEntity.class);
        query.from(ConnectionEntity.class);
        
        return getSession().createQuery(query).getResultList();
    }

    public List<ConnectionEntity> listAllConnectionsShortInfo () {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(ConnectionEntity.class);
        Root<ConnectionEntity> root = query.from(ConnectionEntity.class);
        query.select(builder.construct(ConnectionEntity.class,
                                       root.get(ID_PROPERTY),
                                       root.get(NAME_PROPERTY),
                                       root.get(HOST_PROPERTY),
                                       root.get(PORT_PROPERTY),
                                       root.get(DATABASE_NAME_PROPERTY)));
        
        return getSession().createQuery(query).getResultList();
    }

    public ConnectionEntity findConnection (String name) {
        return getSession().bySimpleNaturalId(ConnectionEntity.class).load(name);
    }

    public void deleteConnection (String name) {
        ConnectionEntity connectionFromDB = findConnection(name);

        if(connectionFromDB != null) {
            delete(connectionFromDB);
        }
    }

    public void updateConnection (Map<String, String> newValuesMap, String name) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaUpdate<ConnectionEntity> update = builder.createCriteriaUpdate(ConnectionEntity.class);
        Root<ConnectionEntity> root = update.from(ConnectionEntity.class);

        for (Map.Entry<String,String> newValue : newValuesMap.entrySet()) {
            update.set(newValue.getKey(), newValue.getValue());
        }

        update.where(builder.equal(root.get(NAME_PROPERTY), name));

        getSession().createQuery(update).executeUpdate();
    }

}
