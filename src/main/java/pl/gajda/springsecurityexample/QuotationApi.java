package pl.gajda.springsecurityexample;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // klasa która świadczy usługi Restowe
public class QuotationApi {


    private List<Quotation> quotations;

    public QuotationApi() {
        this.quotations = new ArrayList<>();
        quotations.add(new Quotation("Wiem, ze nic nie wiem", "Wiesiek2"));
        quotations.add(new Quotation("Rampampampam", "Popek"));
    }


    @GetMapping("/api")
    public List<Quotation> getQuotation() {

        return quotations;
    }

    @PostMapping("/api")
    public boolean addQuotation (@RequestBody Quotation quotation) {
        return quotations.add(quotation);
    }

    @DeleteMapping("/api")
    public void deleteQuotation (@RequestParam int index) {
        quotations.remove(index);
    }
}
