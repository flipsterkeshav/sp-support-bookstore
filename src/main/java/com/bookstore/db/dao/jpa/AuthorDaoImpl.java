package com.bookstore.db.dao.jpa;

import com.bookstore.db.dao.api.AuthorDao;
import com.bookstore.db.model.entity.Author;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Provider;
import javax.persistence.EntityManager;

/**
 * Created by keshav.gupta on 08/07/16.
 */
@Slf4j
public class AuthorDaoImpl extends BaseDaoJpaImpl<Author, Long> implements AuthorDao {

    @Inject
    public AuthorDaoImpl(Provider<EntityManager> entityManagerProvider) {
        super(entityManagerProvider);
        entityClass = Author.class;
    }


//    @Override
//    public HashMap<String, Long> getAreaDetails(Long cityId) {
//        log.info("getting area details for cityId: {}", cityId);
//        HashMap<String, Long> areaDetails = new HashMap<String, Long>();
//        List<City> cityList = new ArrayList<City>();
//        StringBuilder queryString = new StringBuilder(" select c from City c where c.id = :cityId");
//        EntityManager em = getEntityManager();
//        Query query = em.createQuery(queryString.toString()).setParameter("cityId", cityId);
//        cityList = query.getResultList();
//
//        City city = null;
//        if(cityList!=null && cityList.size()!=0){
//            city = cityList.get(0);
//            List<Area> areaList = city.getAreaList();
//            for(Area a: areaList)
//                areaDetails.put(a.getAreaName(), a.getId());
//        }
//        else
//            return  null;
//
//        return areaDetails;
//    }

//    @Override
//    public Author findById(final Long id) {
//        Session session = (Session) getEntityManager().getDelegate();
//        Criteria criteria = session.createCriteria(Author.class);
//        criteria.add( Restrictions.idEq(id));
//        List results = criteria.list();
//        Author area = (Author)results.get(0);
//        return area;
//    }

//    /**
//     * returns the list of areas on like criteria.
//     */
//    @Override
//    public List<Area> findLikeAreaName(String areaName) {
//        Session session = (Session) getEntityManager().getDelegate();
//        Criteria criteria = session.createCriteria(Area.class);
//        criteria.add( Restrictions.like("areaName",areaName+"%"));
//        List<Area> results = criteria.list();
//        return results;
//    }

//    @Override
//    public List<Area> getAreaForPincode(Long pincode) {
//        Session session = (Session) getEntityManager().getDelegate();
//        Criteria criteria = session.createCriteria(Area.class);
//        criteria.add( Restrictions.eq("pincode", pincode));
//        List<Area> results = criteria.list();
//        return results;
//    }

//    @Override
//    public List<Area> searchAreaDetails(AreaSearchParamDto areaSearchParamDto) {
//        StringBuilder queryString = new StringBuilder("");
//        List<String> queryParam = new ArrayList<>();
//        ImmutableMap.Builder<String, Object> namedParamMapBuilder = ImmutableMap.<String, Object>builder();
//
//        if(StringUtils.isNotEmpty(areaSearchParamDto.getCityName())){
//            queryString.append("select a from City c inner join c.areaList a");
//            queryParam.add("c.cityName = :cityName");
//            namedParamMapBuilder.put("cityName", areaSearchParamDto.getCityName());
//        }else{
//            queryString.append("select a from Area a");
//        }
//
//        if(areaSearchParamDto.getPincode()!=null){
//            queryParam.add("a.pincode = :pincode");
//            namedParamMapBuilder.put("pincode", areaSearchParamDto.getPincode());
//        }
//
//        if(StringUtils.isNotEmpty(areaSearchParamDto.getAreaName())){
//            queryParam.add("a.areaName = :areaName");
//            namedParamMapBuilder.put("areaName", areaSearchParamDto.getAreaName());
//        }
//
//        ImmutableMap<String, Object> namedParamMap = namedParamMapBuilder.build();
//
//        if (namedParamMap.size() > 0) {
//            queryString.append(" where ");
//            queryString.append(StringUtils.join(queryParam, " and "));
//        }
//
//        List<Area> results = findByQueryAndNamedParams(null, null, queryString.toString(),
//                namedParamMap);
//
//        return results != null ? results : Collections.EMPTY_LIST;
//
//        //        Session session = (Session) getEntityManager().getDelegate();
//        //        Criteria criteria = session.createCriteria(Area.class);
//        //        if(areaSearchParamDto.getPincode()!=null)
//        //            criteria.add( Restrictions.eq("pincode", areaSearchParamDto.getPincode()));
//        //        if(StringUtils.isNotEmpty(areaSearchParamDto.getAreaName()))
//        //            criteria.add( Restrictions.eq("areaName", areaSearchParamDto.getAreaName()));
//        //
//        //        List<Area> results = criteria.list();
//        //        return results;
//    }

    @Override
    public Long saveEntity(Author entity) throws Exception {
        return save(entity);
    }

    @Override
    public Author fetchEntity(Long entityId) {
        return findOne(entityId);
    }
}
