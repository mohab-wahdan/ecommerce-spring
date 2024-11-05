package com.example.ecommerce.product.repository;

import com.example.ecommerce.product.dto.SubProductFilterDTO;
import com.example.ecommerce.models.SubProduct;
import com.example.ecommerce.util.PredicateBuilder;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CustomSubProductRepositoryImpl implements CustomSubProductRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public long countSubProductsByFilters(SubProductFilterDTO filterDTO) {


        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<SubProduct> subProductRoot = cq.from(SubProduct.class);
        List<Predicate> predicates = new PredicateBuilder().buildSubProductPredicates(cb, subProductRoot, filterDTO);

        cq.select(cb.count(subProductRoot));
        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<SubProduct> findSubProductsByFilters(SubProductFilterDTO filterDTO) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubProduct> cq = cb.createQuery(SubProduct.class);
        Root<SubProduct> subProductRoot = cq.from(SubProduct.class);

        List<Predicate> predicates = new PredicateBuilder().buildSubProductPredicates(cb, subProductRoot, filterDTO);
        cq.where(predicates.toArray(new Predicate[0]));

        EntityGraph<SubProduct> graph = entityManager.createEntityGraph(SubProduct.class);
        graph.addSubgraph("product");

        TypedQuery<SubProduct> query = entityManager.createQuery(cq)
                .setHint("jakarta.persistence.fetchgraph", graph);
        query.setFirstResult((filterDTO.getPageNumber() - 1) * filterDTO.getPageSize());
        query.setMaxResults(filterDTO.getPageSize());

        return query.getResultList();
    }

    @Override
    public SubProduct findSubCategoryById(String searchId) {

//        CriteriaBuilder cb = JpaUtil.getEntityManagerFactory().getCriteriaBuilder();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SubProduct> q = cb.createQuery(SubProduct.class);
        Root<SubProduct> sub = q.from(SubProduct.class);
        q.select(sub).where(
                cb.and(
                        cb.equal(sub.get("id"), searchId),
                        cb.equal(sub.get("isDeleted"), false)
                )
        );
        return entityManager.createQuery(q).getSingleResult();
    }
}
