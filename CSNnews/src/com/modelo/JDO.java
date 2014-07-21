package com.modelo;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.controlador.PersistenceMF;
import com.google.appengine.api.datastore.Key;

public class JDO<T> {

	final static int FETCH_MAX_RESULTS = 40;
	
	private Class<T> _class;
	
    public static <T> JDO<T> getInstance(Class<T> _class) {
        return new JDO<T>(_class);
    }

    protected JDO(Class<T> _class) {
        this._class = _class;
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findAll(){
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		final Query query = pm.newQuery(_class);
		query.setRange(0, FETCH_MAX_RESULTS);
		List<T> results = ((List<T>) query.execute());
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAllByParameter(String parametro,String tipo, Object valor){
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		Query query = pm.newQuery(_class,parametro+" == _param ");
		query.declareParameters(tipo+" _param");
		List<T> results = (List<T>) query.execute(valor);
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public T findOneByParameter(String parametro,String tipo, Object valor) throws IOException{
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		Query query = pm.newQuery(_class,parametro+" == _param ");
		query.declareParameters(tipo+" _param");
		List<T> results = (List<T>) query.execute(valor);
		if(results.size()<1){throw new IOException("More than one object found");}
		return (results.size()==0)?null:results.get(0);
	}
	
	public T findByKey(Key key){
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		T objeto=pm.getObjectById(_class, key) ;
		pm.close();
		return objeto;
	}
	
	public T findByIdString(String key){
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		T objeto=pm.getObjectById(_class, key) ;
		pm.close();
		return objeto;
	}

	public void insert(T objeto){
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		pm.currentTransaction().begin();
		pm.makePersistent(objeto);
		pm.currentTransaction().commit();
		pm.close();
	}
	
	public void update(T objeto){
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		pm.currentTransaction().begin();
		pm.makePersistent(objeto);
		pm.currentTransaction().commit();
		pm.close();
	}
	
	public void delete(Key key){
		final PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		pm.currentTransaction().begin();
		T object_odl = _class.cast(pm.getObjectById(key, false));
		pm.deletePersistent(object_odl);
		pm.currentTransaction().commit();
	}
}
