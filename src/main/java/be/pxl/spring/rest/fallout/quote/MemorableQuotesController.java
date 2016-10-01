package be.pxl.spring.rest.fallout.quote;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private QuoteAssembler quoteAssembler;

    @RequestMapping(method = RequestMethod.GET)
    public List<QuoteR> all() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"author"})
    public List<QuoteR> byAuthor(@RequestParam("author") String author) {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addQuote(@RequestBody QuoteR newQuoteR){
        return ResponseEntity.ok(null);
    }
}
