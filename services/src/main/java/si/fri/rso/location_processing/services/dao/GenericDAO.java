package si.fri.rso.location_processing.services.dao;



import jdk.jshell.spi.ExecutionControl;


import javax.inject.Inject;
import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.List;

public abstract class GenericDAO<E,PK> {

    @Inject
    protected EntityManager em;

    protected Class<? extends E> entityClass;

    public GenericDAO() {

    }

    public GenericDAO(Class<? extends E> entityClass) {
        this.entityClass = entityClass;
    }

    public List<E> findAll() throws Exception {
        throw new Exception("Not implemented");
    }

    public E findById(PK id) throws InvalidParameterException, EntityNotFoundException {
        E entity;

        try {
            entity = em.find(entityClass, id);
        } catch (javax.persistence.EntityNotFoundException | NoResultException e) {
            throw new EntityNotFoundException("Entity was not found");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalError();
        }
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    //TODO Create custom exceptions
    public E createNew(E instance) throws EntityExistsException,Exception {
        try{
            beginTx();
            em.persist(instance);
            commitTx();
        }catch (Exception e){
            rollbackTx();
        }
        if(instance == null){
            throw new Exception("Null entity");
        }
        return instance;
    }

    public E update(E instance, PK id) throws Exception {
        throw new Exception("Not implemented");
    }

    public Boolean deleteById(PK id)  {
        E entity = findById(id);
        if(entity != null){
            try{
                beginTx();
                em.remove(entity);
                commitTx();
            }catch (Exception e){
                rollbackTx();
            }
        }else{
            return false;
        }

        return true;

    }


    protected void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    protected void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    protected void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

    }
}
