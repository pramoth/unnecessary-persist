package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.MergeContext;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExampleResourceTest {


    @Inject
    EntityManager em;

    @Test
    @Transactional
    void entyty_context() {
        List<Order> resultList = em.createQuery("select s from Order s ", Order.class).getResultList();
        Order order1 = resultList.get(0);
        for (Order order : resultList) {
            order.setName("Order " + order.getId());
            for (OrderItem orderItem : order1.getOrderItems()) {
                orderItem.setName("Hello from Quarkus RESTxxx");
                //em.merge(order);
                //orderItem = em.merge(orderItem);
                System.out.println(orderItem);
            }
        }
    }

    protected void entityIsPersistent(MergeEvent event, Map copyCache) {
        final Object entity = event.getEntity();
        final EventSource source = event.getSession();
        final EntityPersister persister = source.getEntityPersister(event.getEntityName(), entity);
        ((MergeContext) copyCache).put(entity, entity, true);
        cascadeOnMerge(source, persister, entity, copyCache);
        copyValues(persister, entity, entity, source, copyCache);
        event.setResult(entity);
    }

    private void copyValues(EntityPersister persister, Object entity, Object entity1, EventSource source, Map copyCache) {
    }

    private void cascadeOnMerge(EventSource source, EntityPersister persister, Object entity, Map copyCache) {
    }


}