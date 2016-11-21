package be.pxl.spring.rest.fallout.quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, UUID> {


    /*
     * These methods are only here so we have compiling tests
     */
//    void deleteAll();
//
//    List<Quote> save(List<Quote> quotes);
//
//    Quote save(Quote quote);

   List<Quote> findAll();

    List<Quote> findByAuthor(String author);

    List<Quote> findByQuotation(String quote);
}
