package com.rest.api.jdbcjpa.jpa.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.io.Serializable;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

    private static final long serialVersionUID = -6974226432947676697L;

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        EntityTransaction transaction = this.manager.getTransaction();
        boolean owner = false;

        try {
            if (!transaction.isActive()) {
                // truque para fazer rollback no que já passou
                // (senão, um futuro commit, confirmaria até mesmo operações sem transação)
                transaction.begin();
                transaction.rollback();

                // agora sim inicia a transação
                transaction.begin();

                owner = true;
            }

            return context.proceed();
        } catch (Exception e) {
            if (owner) {
                transaction.rollback();
            }

            throw e;
        } finally {
            if (transaction.isActive() && owner) {
                transaction.commit();
            }
        }
    }

}
