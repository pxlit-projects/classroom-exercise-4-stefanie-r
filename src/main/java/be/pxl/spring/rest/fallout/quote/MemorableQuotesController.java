package be.pxl.spring.rest.fallout.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RequestMapping(MemorableQuotesController.QUOTE_BASE_URL)
@RestController
public class MemorableQuotesController {

    public static final String QUOTE_BASE_URL = "/quote";

    public MemorableQuotesController() {
    }

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private QuoteAssembler quoteAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public List<QuoteR> all() {
        List<Quote> qouteList = quoteRepository.findAll();
        List<QuoteR> quoteRList = new ArrayList<>();
        for (Quote quote:qouteList) {
            quoteRList.add(QuoteR.of(
                    quote.getId().toString(),
                    quote.getAuthor(),
                    quote.getQuotation()
            ));
        }
        return quoteRList;
    }

    @RequestMapping(method = RequestMethod.GET, params = {"author"})
    public List<QuoteR> byAuthor(@RequestParam("author") String author)
    {
        List<Quote> qouteList = quoteRepository.findByAuthor(author);
        List<QuoteR> quoteRList = new ArrayList<>();
        for (Quote quote:qouteList) {
            quoteRList.add(QuoteR.of(
                    quote.getId().toString(),
                    quote.getAuthor(),
                    quote.getQuotation()
            ));
        }
        return quoteRList;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json",headers="Accept=application/json")
    public ResponseEntity addQuote(@RequestBody QuoteR newQuoteR) {
        Quote quote = new Quote(newQuoteR.getAuthor(),newQuoteR.getQuote());
        quoteRepository.save(quote);
        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("location", quote.getId().toString());
        return new ResponseEntity<Quote>(quote, httpHeaders,HttpStatus.CREATED);
    }
}
